package staff.view;

import global.io.OutputRenderer;
import main.view.View;
import staff.entity.Staff;

public class StaffUI implements View {
    private final OutputRenderer output;

    public StaffUI(OutputRenderer output) {
        this.output = output;
    }

    @Override
    public void displayHeader() {
        output.println("=====================================");
        output.println("          [사원 POS 시스템]          ");
        output.println("-------------------------------------");
    }

    @Override
    public void displayMenu() {
        output.println("1. 로그인 및 근무 시작");
        output.println("0. 프로그램 종료");
    }


    @Override
    public void displayLast() {
        output.println("번호를 입력하세요>");
    }

    public void displayClose() {
        output.println("프로그램을 종료합니다.");
    }

//----------------------------사용자 로그인 랜더링----------------------------//

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

    public void showLoginSuccess(String userName) {
        output.println("로그인에 성공했습니다.");
        output.printf("%s님 환영합니다.\n", userName);
        output.println("===========================\n");
    }


//----------------------------사용자 상품, 퇴근 분기 랜더링----------------------------//
    public void displayWorkHeader() {
        output.println("=====================================");
        output.println("              [업무 메뉴]              ");
        output.println("-------------------------------------");
    }
    public void displayWorkMenu() {
        output.println("1. 상품 관리");
        output.println("2. 퇴근");
    }


//----------------------------사용자 퇴근 랜더링----------------------------//


    @Override
    public void displayFooter() {
        output.println("-------------------------------------");
        output.println("          [열정 열정 열정 !!]          ");
        output.println("=====================================");
    }
}
