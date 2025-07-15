package main.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.view.StaffLoginUI;
import main.view.StaffUI;
import staff.service.StaffService;

public class StaffMenuController implements Controller{

    private final OutputRenderer output;
    private final InputProvider input;
    private final StaffUI view;
    private final StaffService staffService;

    public StaffMenuController(InputProvider input, OutputRenderer output, StaffUI staffUI, StaffService staffService) {
        this.output = output;
        this.input = input;
        this.view = staffUI;
        this.staffService = staffService;
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
                    view.promptStaffId();
                    int userId = Integer.parseInt(input.readLine());
                    view.promptStaffPassword();
                    int password = Integer.parseInt(input.readLine());
                    staffService.login(userId, password);
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
