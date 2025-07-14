package main.view;

import global.io.OutputRenderer;

public class MainMenuView implements View{
    private final OutputRenderer output;

    public MainMenuView(OutputRenderer output) {
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


    @Override
    public void displayFooter() {
        output.println("-------------------------------------");
        output.println("          [열정 열정 열정 !!]          ");
        output.println("=====================================");
    }
}
