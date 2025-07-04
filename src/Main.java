import information.InformationDao;
import staff.StaffService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StaffService staffService = new StaffService();
        InformationDao informationDao = new InformationDao();

        Scanner sc = new Scanner(System.in);

        System.out.println("아이디 입력: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.println("비밀번호 입력: ");
        int password = sc.nextInt();
        sc.nextLine();

        staffService.login(userId, password);

        while (true) {
            System.out.println("\n메뉴를 선택하세요:");
            System.out.println("1. 계속 근무");
            System.out.println("2. 종료(퇴근)");
            System.out.print("선택: ");
            int menu = sc.nextInt();

            if (menu == 1) {
                System.out.println("근무 중...");
            } else if (menu == 2) {
                System.out.println("\n퇴근을 처리합니다...");
                informationDao.finishWork(userId);
                break;
            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }

        sc.close();
        System.out.println("프로그램을 종료합니다.");
    }
}
