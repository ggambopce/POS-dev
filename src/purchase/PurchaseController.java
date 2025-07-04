package purchase;

import product.Product;
import product.ProductDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PurchaseController {
    private final ProductDao productDao = new ProductDao();
    private final PurchaseDao purchaseDao = new PurchaseDao();
    private final Scanner sc = new Scanner(System.in);

    // 포스 잔고 (임시용)
    private int posBalance = 1_000_000; // 초기 포스 잔고

    public void purchaseProduct() {
        List<PurchaseDetail> details = new ArrayList<>();
        int totalAmount = 0;

        while (true) {
            System.out.println("구매할 제품명 입력 (종료하려면 '0'): ");
            String productName = sc.nextLine();
            if (productName.equals("0")) break;

            Product product = productDao.findByName(productName);
            if (product == null) {
                System.out.println("해당 제품을 찾을 수 없습니다.");
                continue;
            }

            if (product.getExpiryDate().before(new Date())) {
                System.out.println("[주의] 유통기한이 지난 제품입니다.");
                continue;
            }

            if (product.getAdultOnly() == 'Y') {
                System.out.print("미성년자 구매 불가 제품입니다. 성인 인증 번호 입력(6자리): ");
                String auth = sc.nextLine();
                if (!auth.matches("\\d{6}")) {
                    System.out.println("인증 실패. 구매 불가.");
                    continue;
                }
            }

            System.out.print("구매 수량 입력: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            if (quantity > product.getStock()) {
                System.out.println("재고 부족으로 구매할 수 없습니다.");
                continue;
            }

            int price = product.getPrice() * quantity;
            totalAmount += price;

            details.add(new PurchaseDetail(0, quantity, 0, product.getProductId()));

            // 재고 차감은 구매 확정 후 처리
        }

        if (details.isEmpty()) {
            System.out.println("선택된 제품이 없습니다. 구매 취소.");
            return;
        }

        System.out.printf("총 결제 금액: %,d원%n", totalAmount);

        System.out.print("결제 방식 (1: card / 2: cash): ");
        String method = sc.nextLine();

        if (method.equals("2")) {
            System.out.print("현금 투입 금액 입력: ");
            int cash = sc.nextInt();
            sc.nextLine();
            if (cash < totalAmount) {
                System.out.println("현금이 부족합니다.");
                return;
            }
            int change = cash - totalAmount;
            posBalance += totalAmount; // 현금은 포스에 돈이 들어옴
            System.out.printf("거스름돈: %,d원%n", change);
        } else if (method.equals("1")) {
            posBalance += totalAmount; // 카드결제 시 포스잔고 바로 반영
            System.out.println("카드 결제가 완료되었습니다.");
        } else {
            System.out.println("지원하지 않는 결제 방식입니다.");
            return;
        }

        Purchase purchase = new Purchase(0, new Date());
        purchaseDao.savePurchase(purchase, details);

        // 재고 차감 처리
        for (PurchaseDetail detail : details) {
            Product product = productDao.findById(detail.getProductId());
            int newStock = product.getStock() - detail.getPurchaseQuantity();
            productDao.updateStock(product.getProductId(), newStock);
        }

        System.out.println("구매가 완료되었습니다.");
        System.out.printf("현재 포스 잔고: %,d원%n", posBalance);
    }
}