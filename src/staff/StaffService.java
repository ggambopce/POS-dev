package staff;

import information.InformationDao;

import java.util.Date;

public class StaffService {

    private StaffDao staffDao = new StaffDao();
    private InformationDao informationDao = new InformationDao();

    public void login(int userId, int password) {
        Staff staff = staffDao.login(userId, password);

        if (staff != null) {
            System.out.println(staff.userName + "안녕하세요.");

            Date now = new Date();
            informationDao.saveLoginInfo(staff.getUserId(), now);
            System.out.println("로그인 시간이 기록되었습니다: " + now);
        } else {
            System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
