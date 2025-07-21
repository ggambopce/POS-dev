package staff.controller;

import global.util.MessageBox;
import global.io.InputProvider;
import global.io.OutputRenderer;
import main.controller.Controller;
import staff.entity.Staff;
import staff.service.StaffService;
import staff.service.StaffServiceImplement;
import staff.view.StaffUI;

public class StaffLoginController implements Controller {
    private final InputProvider input;
    private final OutputRenderer output;
    private final StaffUI view;
    StaffService staffService;


    public StaffLoginController(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
        this.view = new StaffUI(output);
        staffService = new StaffServiceImplement();
    }

    @Override
    public void run() {
        while (true) {
            view.displayHeader();
            view.promptStaffId();
            int userId = Integer.parseInt(input.readLine());
            if (staffService.checkStaffId(userId)){ // 올바른 아이디인 경우
                view.promptStaffPassword();
                int password = Integer.parseInt(input.readLine());
                Staff staff = staffService.login(userId, password);

                view.showLoginSuccess(staff.getUserName());
            } else {
                view.showLoginFailById();
                MessageBox.showEnterToContinue(input, output);
                continue;
            }

            MessageBox.showEnterToContinue(input, output);
            Controller controller = new StaffProductController(input, output);
            controller.run();

        }
    }







}
