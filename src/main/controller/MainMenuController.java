package main.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.view.MainMenuView;
import staff.controller.StaffController;

public class MainMenuController implements Controller{

    private final OutputRenderer output;
    private final InputProvider input;

    public MainMenuController(InputProvider input, OutputRenderer output) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void run() {
        MainMenuView view = new MainMenuView(output);

        while (true) {
            view.displayHeader();

            view.displayMenu();
            view.displayLast();
            String choice = input.readLine();
            if (choice == null || choice.isBlank()) {
                MessageBox.showWarningByWrongInput(input, output);
                continue;
            }

            Controller controller = null;
            switch (choice) {
                // 로그인 및 근무 시작
                case "1" :
                    controller = new StaffController(input, output);
                    break;
                // 프로그램 종료
                case "0" :
                    view.displayClose();
                    return;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    continue;
            }

            if (controller !=null) {
                controller.run();
            }

            view.displayFooter();


        }
    }
}
