package product;

import purchase.PurchaseController;
import stock.StockController;

import java.util.Scanner;

public class ProductUI {
    private final Scanner sc = new Scanner(System.in);
    private final ProductController controller = new ProductController();
    private final PurchaseController purchaseController = new PurchaseController();
    private final StockController stockController = new StockController();

    public void start() {
        while (true) {
            System.out.println("\n===== 제품 관리 메뉴 =====");
            System.out.println("1. 구매");
            System.out.println("2. 입고 등록");
            System.out.println("3. 전체 제품 목록 보기");
            System.out.println("4. 제품명으로 검색");
            System.out.println("5. 신제품 등록");
            System.out.println("0. 종료");
            System.out.print("선택 > ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> purchaseController.purchaseProduct();
                case 2 -> stockController.registerStock();
                case 3 -> controller.showAllProducts();
                case 4 -> controller.searchByProductName();
                case 5 -> controller.registerProduct();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }
}
