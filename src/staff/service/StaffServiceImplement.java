package staff.service;

import information.repository.InformationDao;
import staff.entity.Staff;
import staff.repository.StaffDaoImplement;

import java.util.Date;

public class StaffServiceImplement implements StaffService {


    private final StaffDaoImplement staffDaoImplement = new StaffDaoImplement();
    private final InformationDao informationDao = new InformationDao();

    public void login(int userId, int password) {
        Staff staff = staffDaoImplement.login(userId, password);

        if (staff != null) {
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

    @Override
    public void finishWork() {

    }
}


//    public boolean isLogin() {
//        return loggedIn;
//    }
//
//    public void finish() {
//        if (loggedIn) {
//            staffController.finishWork();
//            loggedIn = false;
//            finishRequested = true;
//        }
//    }
//
//    public boolean isFinishRequested() {
//        return finishRequested;
//    }

//    // 2. 퇴근 처리 메서드
//    public void finishWork() {
//        if (loggedInStaff == null) {
//            System.out.println("현재 로그인한 사원이 없습니다.");
//            return;
//        }
//
//        infoDao.finishWork(loggedInStaff.getUserId());
//        loggedInStaff = null; // 세션 초기화
//    }
//
//    // 로그인 상태 확인
//    public boolean isLoggedIn() {
//        return loggedInStaff != null;
//    }