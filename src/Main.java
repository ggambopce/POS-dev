import product.view.ProductUI;
import staff.view.StaffUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StaffUI staffUI = new StaffUI();
        ProductUI productUI = new ProductUI();
        staffUI.start();

        // 2. 로그인 성공 여부 확인
        if (staffUI.isLoggedIn()) {
            while (true) {
                System.out.println("""
                ========================
                [업무 메뉴]
                1. 상품 관리
                2. 퇴근
                ========================
                선택 >
                """);

                String choice = new java.util.Scanner(System.in).nextLine();

                switch (choice) {
                    case "1" -> productUI.start();  // 상품 관리
                    case "2" -> {
                        staffUI.finish();          // 퇴근 처리
                        return;                    // 프로그램 종료
                    }
                    default -> System.out.println("올바른 메뉴를 선택하세요.");
                }
            }
        } else {
            System.out.println("로그인 실패. 프로그램을 종료합니다.");
        }
    }
}