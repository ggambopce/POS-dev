package global.io;

public interface OutputProvider {
    void print(String message);
    void println(String message);
    void printf(String message, Object... args);
}
