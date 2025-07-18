package global.SessionManager;

import staff.entity.Staff;

public class SessionContext implements SessionManager {
    private Staff currentUser;

    @Override
    public void login(Staff staff) {
        this.currentUser = staff;
    }

    @Override
    public void logout() {
        this.currentUser = null;
    }

    @Override
    public Staff getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
