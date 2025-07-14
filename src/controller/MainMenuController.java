package controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import view.MainMenuView;

public class MainMenuController implements Controller {
    private final InputProvider input;
    private final OutputRenderer output;
    private final MainMenuView view;

    // 생성자 주입
    public MainMenuController(InputProvider input, OutputRenderer output, MainMenuView view) {
        this.input = input;
        this.output = output;
        this.view = view;
    }


    @Override
    public void run() {
        view.displayHeader();
        view.displayBody();
        view.displayFooter();
    }
}
