import staff.StaffService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StaffService staffService = new StaffService();
        Scanner sc = new Scanner(System.in);

        System.out.println("아이디 입력: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.println("비밀번호 입력: ");
        int password = sc.nextInt();
        sc.nextLine();

        staffService.login(userId, password);
    }
}
