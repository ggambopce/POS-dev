package staff.view;

import staff.controller.StaffController;
import view.View;

import java.util.Scanner;

public class StaffUI implements View {
    private final StaffController staffController = new StaffController();
    private final Scanner sc = new Scanner(System.in);

    private boolean loggedIn = false;
    private boolean finishRequested = false;


    public void start() {
        System.out.println("""
            ==============================
            [ 사원 로그인 시스템 ]
            1. 로그인 및 근무 시작
            0. 프로그램 종료
            ==============================
            """);

        System.out.print("선택> ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1" -> {
                staffController.loginAndStartWork();
                if (staffController.isLoggedIn()) {
                    loggedIn = true;
                }
            }
            case "0" -> System.out.println("프로그램을 종료합니다.");
            default -> System.out.println("올바른 메뉴를 선택하세요.");
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void finish() {
        if (loggedIn) {
            staffController.finishWork();
            loggedIn = false;
            finishRequested = true;
        }
    }

    public boolean isFinishRequested() {
        return finishRequested;
    }

    @Override
    public void displayHeader() {

    }

    @Override
    public void displayBody() {

    }

    @Override
    public void displayFooter() {

    }
}
