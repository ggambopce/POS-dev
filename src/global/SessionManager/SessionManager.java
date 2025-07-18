package global.SessionManager;

import staff.entity.Staff;

public interface SessionManager {
    void login(Staff staff);
    void logout();
    Staff getCurrentUser();
    boolean isLoggedIn();
}
