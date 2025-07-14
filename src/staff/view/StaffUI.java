package staff.view;

import main.view.View;

import java.util.Scanner;

public class StaffUI implements View {
//    private final StaffController staffController = new StaffController();
    private final Scanner sc = new Scanner(System.in);

    private boolean loggedIn = false;
    private boolean finishRequested = false;

//    public boolean isLoggedIn() {
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

    }

    @Override
    public void displayMenu() {

    }

    @Override
    public void displayLast() {

    }

    @Override
    public void displayFooter() {

    }
}
