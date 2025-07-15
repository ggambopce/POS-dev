package main.controller;

import global.util.MessageBox;
import global.io.InputProvider;
import global.io.OutputRenderer;
import staff.service.StaffService;
import staff.service.StaffServiceImplement;
import main.view.StaffUI;

public class StaffController implements Controller {
    private final InputProvider input;
    private final OutputRenderer output;
    private final StaffUI view;

    StaffService staffService;


    public StaffController(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
        this.view = new StaffUI(output);
        staffService = new StaffServiceImplement();
    }

    @Override
    public void run() {
        while (true) {
            view.displayHeader();

            view.promptStaffId();
            int userId = Integer.parseInt(input.readLine());
            if (staffService.checkStaffId(userId)){ // 올바른 아이디인 경우
                view.promptStaffPassword();
                int password = Integer.parseInt(input.readLine());
                staffService.login(userId, password);
            } else {
                view.showLoginFailById();
                MessageBox.showEnterToContinue(input, output);
                continue;
            }

            view.showLoginSuccess();
            MessageBox.showEnterToContinue(input, output);
            Controller controller = new ProductController(input, output);
            controller.run();

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






//    // 1. 로그인 전용 메서드
//    public void loginAndStartWork() {
//        System.out.print("사원 ID 입력: ");
//        int id = sc.nextInt();
//
//        System.out.print("비밀번호 입력: ");
//        int password = sc.nextInt();
//        sc.nextLine(); // 개행 제거
//
//        loggedInStaff = staffDaoImplement.login(id, password);
//
//        if (loggedInStaff == null) {
//            System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
//            return;
//        }
//
//        System.out.printf("사원 %s님 로그인 성공!\n", loggedInStaff.getUserName());
//        infoDao.saveLoginInfo(loggedInStaff.getUserId(), new Date());
//    }
//
//    // 2. 퇴근 처리 메서드
//    public void finishWork() {
//        if (loggedInStaff == null) {
//            System.out.println("현재 로그인한 사원이 없습니다.");
//            return;
//        }
//
//        infoDao.finishWork(loggedInStaff.getUserId());
//        loggedInStaff = null; // 세션 초기화
//    }
//
//    // 로그인 상태 확인
//    public boolean isLoggedIn() {
//        return loggedInStaff != null;
//    }


}
