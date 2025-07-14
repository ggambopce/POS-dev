import main.controller.Controller;
import main.controller.MainMenuController;
import global.io.CLIInputProvider;
import global.io.CLIOutputRenderer;
import global.io.InputProvider;
import global.io.OutputRenderer;
import staff.entity.Staff;

public class Main {
    public  static Staff staff;

    public static void main(String[] args) {
        // I/O 시스템 추상화 객체 생성
        InputProvider input = new CLIInputProvider();
        OutputRenderer output = new CLIOutputRenderer();

        // 컨트롤러 초기화
        Controller controller = new MainMenuController(input, output);
        controller.run();

//        StaffUI staffUI = new StaffUI();// 로그인 등 실제 흐름 담당


//        ProductUI productUI = new ProductUI();
//        staffUI.start();
//
//        // 2. 로그인 성공 여부 확인
//        if (staffUI.isLoggedIn()) {
//            while (true) {
//                System.out.println("""
//                ========================
//                [업무 메뉴]
//                1. 상품 관리
//                2. 퇴근
//                ========================
//                선택 >
//                """);
//
//                String choice = new java.util.Scanner(System.in).nextLine();
//
//                switch (choice) {
//                    case "1" -> productUI.start();  // 상품 관리
//                    case "2" -> {
//                        staffUI.finish();          // 퇴근 처리
//                        return;                    // 프로그램 종료
//                    }
//                    default -> System.out.println("올바른 메뉴를 선택하세요.");
//                }
//            }
//        } else {
//            System.out.println("로그인 실패. 프로그램을 종료합니다.");
//        }
    }
}