package staff.controller;

import main.controller.Controller;
import global.io.InputProvider;
import global.io.OutputRenderer;
import staff.entity.Staff;
import staff.view.StaffUI;

public class StaffController implements Controller {
    private final InputProvider input;
    private final OutputRenderer output;
    private final StaffUI staffUI;



    private Staff loggedInStaff = null;

    public StaffController(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
        this.staffUI = null;
    }


    @Override
    public void run() {


    }








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
