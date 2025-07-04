package purchase;

import java.util.Scanner;

public class PurchaseUI {
    private final Scanner sc = new Scanner(System.in);

    public void showMenu(PurchaseController controller) {
        while (true) {
            System.out.println("\n===== 구매 메뉴 =====");
            System.out.println("1. 제품 구매");
            System.out.println("0. 돌아가기");
            System.out.print("메뉴 선택: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> controller.purchaseProduct();
                case 0 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }

        }
    }
}
