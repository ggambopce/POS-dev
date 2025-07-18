package staff.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.controller.Controller;
import staff.view.StaffUI;

public class StaffMenuController implements Controller {

    private final OutputRenderer output;
    private final InputProvider input;
    private final StaffUI view;
    private final StaffLoginController staffLoginController;

    public StaffMenuController(InputProvider input, OutputRenderer output) {
        this.output = output;
        this.input = input;
        this.view = new StaffUI(output);
        this.staffLoginController = new StaffLoginController(input,output);
    }

    @Override
    public void run() {

        while (true) {
            view.displayHeader();
            view.displayMenu();
            view.displayLast();
            String choice = input.readLine();
            if (choice == null || choice.isBlank()) {
                MessageBox.showWarningByWrongInput(input, output);
                continue;
            }

            switch (choice) {
                // 로그인 및 근무 시작
                case "1" :

                    staffLoginController.run();
                    break;

                // 프로그램 종료
                case "0" :
                    view.displayClose();
                    return;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    continue;
            }

            view.displayFooter();

        }
    }
}
