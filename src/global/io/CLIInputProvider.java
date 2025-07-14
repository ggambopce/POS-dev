package global.io;

import java.util.Scanner;

public class CLIInputProvider implements InputProvider {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
