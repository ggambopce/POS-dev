package staff;

import information.InformationDao;

import java.util.Date;
import java.util.Scanner;

public class StaffController {
    private final StaffDao staffDao = new StaffDao();
    private final InformationDao infoDao = new InformationDao();
    private final Scanner sc = new Scanner(System.in);

    private Staff loggedInStaff = null;

    // 1. 로그인 전용 메서드
    public void loginAndStartWork() {
        System.out.print("사원 ID 입력: ");
        int id = sc.nextInt();

        System.out.print("비밀번호 입력: ");
        int password = sc.nextInt();
        sc.nextLine(); // 개행 제거

        loggedInStaff = staffDao.login(id, password);

        if (loggedInStaff == null) {
            System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
            return;
        }

        System.out.printf("사원 %s님 로그인 성공!\n", loggedInStaff.getUserName());
        infoDao.saveLoginInfo(loggedInStaff.getUserId(), new Date());
    }

    // 2. 퇴근 처리 메서드
    public void finishWork() {
        if (loggedInStaff == null) {
            System.out.println("현재 로그인한 사원이 없습니다.");
            return;
        }

        infoDao.finishWork(loggedInStaff.getUserId());
        loggedInStaff = null; // 세션 초기화
    }

    // 로그인 상태 확인
    public boolean isLoggedIn() {
        return loggedInStaff != null;
    }

}
