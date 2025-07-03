package model;

public class Staff {
    int userId;
    int password;

    public Staff(int userId, int password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}


