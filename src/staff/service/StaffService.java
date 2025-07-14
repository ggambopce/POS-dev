package staff.service;

public interface StaffService {

    public void login(int userId, int password);

    boolean checkStaffId(int staffId);
}
