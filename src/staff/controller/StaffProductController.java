package staff.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.controller.Controller;
import product.controller.ProductMenuController;
import product.service.ProductService;
import product.service.ProductServiceImplement;
import staff.service.StaffService;
import staff.service.StaffServiceImplement;
import staff.view.StaffUI;

public class StaffProductController implements Controller {
    private final InputProvider input;
    private final OutputRenderer output;
    private final StaffUI view;
    private final ProductMenuController productMenuController;
    private final StaffService staffService;

    public StaffProductController(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
        this.view = new StaffUI(output);
        this.staffService = new StaffServiceImplement();
        this.productMenuController = new ProductMenuController(input, output);
    }

    @Override
    public void run() {
        view.displayWorkHeader();
        view.displayWorkMenu();
        view.displayLast();
        String choice = input.readLine();
        while (true) {
            if (choice == null || choice.isBlank()) {
                MessageBox.showWarningByWrongInput(input, output);
                continue;
            }

            switch (choice) {
                // 1. 상품관리
                case "1" :
                    productMenuController.run();
                    break;
                // 2. 퇴근
                case "2" :
                    staffService.finishWork();
                    break;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    continue;
            }
        }
    }
}

//    StaffUI staffUI = new StaffUI();// 로그인 등 실제 흐름 담당
//
//
//    ProductUI productUI = new ProductUI();
//        staffUI.start();
//
//    // 2. 로그인 성공 여부 확인
//        if (staffUI.isLoggedIn()) {
//        while (true) {
//            System.out.println("""
//                ========================
//                [업무 메뉴]
//                1. 상품 관리
//                2. 퇴근
//                ========================
//                선택 >
//                """);
//
//            String choice = new java.util.Scanner(System.in).nextLine();
//
//            switch (choice) {
//                case "1" -> productUI.start();  // 상품 관리
//                case "2" -> {
//                    staffUI.finish();          // 퇴근 처리
//                    return;                    // 프로그램 종료
//                }
//                default -> System.out.println("올바른 메뉴를 선택하세요.");
//            }
//        }
//    } else {
//        System.out.println("로그인 실패. 프로그램을 종료합니다.");
//    }
