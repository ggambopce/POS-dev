import main.controller.Controller;
import main.controller.StaffMenuController;
import global.io.CLIInputProvider;
import global.io.CLIOutputRenderer;
import global.io.InputProvider;
import global.io.OutputRenderer;
import main.view.StaffLoginUI;
import main.view.StaffMenuUI;
import staff.entity.Staff;

public class Main {
    public  static Staff staff;

    public static void main(String[] args) {
        // I/O 시스템 추상화 객체 생성
        InputProvider input = new CLIInputProvider();
        OutputRenderer output = new CLIOutputRenderer();
        StaffMenuUI staffMenuUI = new StaffMenuUI(output);


        // 컨트롤러 초기화
        Controller controller = new StaffMenuController(input, output, staffMenuUI);
        controller.run();

    }
}