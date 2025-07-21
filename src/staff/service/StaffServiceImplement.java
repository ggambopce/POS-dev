package staff.service;

import global.SessionManager.SessionContext;
import global.SessionManager.SessionManager;
import information.repository.InformationDao;
import staff.entity.Staff;
import staff.repository.StaffDaoImplement;

import java.util.Date;

public class StaffServiceImplement implements StaffService {

    private final StaffDaoImplement staffDaoImplement = new StaffDaoImplement();
    private final InformationDao informationDao = new InformationDao();
    private final SessionManager sessionManager = SessionContext.getInstance();

    public void login(int userId, int password) {

        Staff staff = staffDaoImplement.login(userId, password);

        if (staff != null) {
            sessionManager.login(staff); // 로그인 성공시 세션에 저장
            System.out.println(staff.getUserName() + "안녕하세요.");

            Date now = new Date();
            informationDao.saveLoginInfo(staff.getUserId(), now);
            System.out.println("로그인 시간이 기록되었습니다: " + now);
        } else {
            System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    @Override
    public boolean checkStaffId(int staffId) {
        return false;
    }

    // 퇴근과 함께 시급 계산
    @Override
    public void finishWork() {
        if (!sessionManager.isLoggedIn()) {
            System.out.println("로그인한 사용자가 없습니다.");
        }

        Staff staff = sessionManager.getCurrentUser();
        informationDao.finishWork(staff.getUserId());
        sessionManager.logout();

    }
}
