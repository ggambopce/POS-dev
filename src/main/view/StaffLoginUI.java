package main.view;

import global.io.OutputRenderer;
import staff.entity.Staff;

public class StaffLoginUI implements View {

    private final OutputRenderer output;
    private boolean isLogin;

    public StaffLoginUI(OutputRenderer output) {

        this.output = output;
        isLogin = false;
    }

//    public boolean isLogin() {
//        return loggedIn;
//    }
//
//    public void finish() {
//        if (loggedIn) {
//            staffController.finishWork();
//            loggedIn = false;
//            finishRequested = true;
//        }
//    }
//
//    public boolean isFinishRequested() {
//        return finishRequested;
//    }

    @Override
    public void displayHeader() {
        output.println("=====================================");
        output.println("          [사원 POS 로그인]          ");
        output.println("-------------------------------------");
    }

    @Override
    public void displayMenu() {
    }

    @Override
    public void displayLast() {

    }

    public void promptStaffId() {
        output.println("사원 아이디를 입력하세요.");
    }

    public void promptStaffPassword() {
        output.println("비밀번호를 입력하세요.");
    }

    public void showLoginFailById() {
        output.println("아이디를 잘못 입렵했습니다.");
    }

    public void showLoginFailByPassword() {
        output.println("비밀번호를 잘못 입력했습니다.");
    }

    public void showLoginSuccess() {
        output.println("로그인에 성공했습니다.");
        output.printf("%s님 환영합니다.\n", Staff.class.getName());
        output.println("===========================\n");
    }

    @Override
    public void displayFooter() {
        output.println("-------------------------------------");
        output.println("          [열정 열정 열정 !!]          ");
        output.println("=====================================");
    }


    public void displayClose() {
    }
}
