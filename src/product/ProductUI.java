package product;

import purchase.PurchaseController;
import purchase.PurchaseUI;

import java.util.Scanner;

public class ProductUI {
    private final Scanner sc = new Scanner(System.in);
    private final ProductController controller = new ProductController();
    private final PurchaseUI purchaseUI = new PurchaseUI();
    private final PurchaseController purchaseController = new PurchaseController();

    public void start() {
        while (true) {
            System.out.println("\n===== 제품 관리 메뉴 =====");
            System.out.println("1. 구매");
            System.out.println("2. 제품 등록");
            System.out.println("3. 전체 제품 목록 보기");
            System.out.println("4. 제품명으로 검색");
            System.out.println("0. 종료");
            System.out.print("선택 > ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> purchaseUI.showMenu(purchaseController);
                case 2 -> controller.registerProduct();
                case 3 -> controller.showAllProducts();
                case 4 -> controller.searchByProductName();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }
}
