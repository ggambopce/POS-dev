package staff.service;

public interface StaffService {

    void login(int userId, int password);

    boolean checkStaffId(int staffId);

    void finishWork();
}
