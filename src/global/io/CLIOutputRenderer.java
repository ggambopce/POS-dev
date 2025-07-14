package global.io;

public class CLIOutputRenderer implements OutputRenderer {
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printf(String message, Object... args) {
        System.out.printf(message, args);
    }
}
