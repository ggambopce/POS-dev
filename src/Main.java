import main.controller.Controller;
import main.controller.StaffLoginController;
import global.io.CLIInputProvider;
import global.io.CLIOutputRenderer;
import global.io.InputProvider;
import global.io.OutputRenderer;
import staff.entity.Staff;

public class Main {
    public  static Staff staff;

    public static void main(String[] args) {
        // I/O 시스템 추상화 객체 생성
        InputProvider input = new CLIInputProvider();
        OutputRenderer output = new CLIOutputRenderer();

        // 컨트롤러 초기화
        Controller controller = new StaffLoginController(input, output);
        controller.run();

    }
}