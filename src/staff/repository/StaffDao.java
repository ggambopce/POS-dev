package staff.repository;

import staff.entity.Staff;

public interface StaffDao {

    public Staff login(int userId, int password);
}
