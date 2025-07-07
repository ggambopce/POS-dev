package purchase;

import product.Product;
import product.ProductDao;
import stock.Stock;
import stock.StockDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PurchaseController {
    private final ProductDao productDao = new ProductDao();
    private final PurchaseDao purchaseDao = new PurchaseDao();
    private final StockDao stockDao = new StockDao();
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

            if (product.getAdultOnly() == 'Y') {
                System.out.print("미성년자 구매 불가 제품입니다. 주민등록증을 확인해주세요.");
            }

            System.out.print("구매 수량 입력: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            if (quantity > product.getStock()) {
                System.out.println("재고 부족으로 구매할 수 없습니다.");
                continue;
            }

            // 재고 차감 대상 리스트 확보
            List<Stock> availableStocks = stockDao.findAvailableStocksByProductId(product.getProductId(), quantity);
            int remaining = quantity;

            for (Stock stock : availableStocks) {
                int deductQty = Math.min(remaining, stock.getQuantity());

                // 상세 기록 추가 (stockId 포함)
                details.add(new PurchaseDetail(0, deductQty, 0, product.getProductId(), stock.getStockId()));

                remaining -= deductQty;
                if (remaining == 0) break;
            }

            if (remaining > 0) {
                System.out.println("입고 기준으로 재고가 부족합니다.");
                return;
            }

            totalAmount += product.getPrice() * quantity;
        }

        if (details.isEmpty()) {
            System.out.println("선택된 제품이 없습니다. 구매 취소.");
            return;
        }

        System.out.printf("총 결제 금액: %,d원%n", totalAmount);

        // 구매 테이블 저장
        Purchase purchase = new Purchase(0, new Date(), totalAmount);
        purchaseDao.savePurchase(purchase, details);

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
            posBalance += totalAmount;
            System.out.printf("거스름돈: %,d원%n", change);
        } else if (method.equals("1")) {
            posBalance += totalAmount;
            System.out.println("카드 결제가 완료되었습니다.");
        } else {
            System.out.println("지원하지 않는 결제 방식입니다.");
            return;
        }

        // 결과 출력
        for (PurchaseDetail detail : details) {
            Product product = productDao.findById(detail.getProductId());
            System.out.printf("제품 [%s] 남은 재고: %d개%n", product.getProductName(), product.getStock());
        }

        System.out.println("구매가 완료되었습니다.");
        System.out.printf("현재 포스 잔고: %,d원%n", posBalance);
    }

}