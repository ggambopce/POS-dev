package global.SessionManager;

import staff.entity.Staff;

/**
 * 전역에서 하나의 Context인스턴스만 사용해야 세션이 유지된다
 * 싱글톤 패턴을 사용하여 세션을 적용
 */
public class SessionContext implements SessionManager {

    // 전영에서 사용할 싱글톤 인스턴스 생성
    private static final SessionContext instance = new SessionContext();
    private Staff currentUser;
    // 디폴트 생성자 자동 생성

    // 전역에서 사용할 생성된 싱글톤 인스턴스 getter메서드
    public static SessionContext getInstance() {
        return instance;
    }

    // 로그인 상태 setter메서드
    @Override
    public void login(Staff staff) {
        this.currentUser = staff;
    }

    // 로그아웃 상태 setter메서드
    @Override
    public void logout() {
        this.currentUser = null;
    }

    // 로그인 상태 getter메서드
    @Override
    public Staff getCurrentUser() {
        return currentUser;
    }

    // 로그인 상태 정보
    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
