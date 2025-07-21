package staff.service;

import staff.entity.Staff;

public interface StaffService {

    Staff login(int userId, int password);

    boolean checkStaffId(int staffId);

    void finishWork();
}
