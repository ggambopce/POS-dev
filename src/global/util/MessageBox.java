package global.util;

import global.io.InputProvider;
import global.io.OutputRenderer;

/**
 *  객체를 생성하지 않고 사용 가능한 메세지 출력 static 메서드
 */
public class MessageBox {
    public static void showEnterToContinue(InputProvider input, OutputRenderer output) {
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }

    public static void showWarningByWrongInput(InputProvider input, OutputRenderer output)
    {
        output.println("\n잘못된 입력입니다. 다시 입력해주세요.\n");
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }
    public static void showWarningByWrongInputGotoMainMenu(InputProvider input, OutputRenderer output)
    {
        output.println("\n잘못된 입력입니다. 메인메뉴로 돌아갑니다.\n");
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }
    public static void showWarningByWrongNumber(InputProvider input, OutputRenderer output)
    {
        output.println("\n숫자를 입력해주세요.\n");
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }
}
