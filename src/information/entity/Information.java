package information.entity;

import java.util.Date;

public class Information {
    int informationId;
    int completeTotalSales;         // 종료시점 매출
    int completeWage;               // 종료시점 시급
    int completeBalance;            // 종료시점 잔고
    Date loginTime;                 // 로그인 시작 시간
    Date completeTime;              // 종료시점 시간
    int userId;                     // 외래키

    public Information(int informationId, int completeTotalSales, int completeWage, int completeBalance, Date loginTime, Date completeTime, int userId) {
        this.informationId = informationId;
        this.completeTotalSales = completeTotalSales;
        this.completeWage = completeWage;
        this.completeBalance = completeBalance;
        this.loginTime = loginTime;
        this.completeTime = completeTime;
        this.userId = userId;
    }

    public int getInformationId() {
        return informationId;
    }

    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    public int getCompleteTotalSales() {
        return completeTotalSales;
    }

    public void setCompleteTotalSales(int completeTotalSales) {
        this.completeTotalSales = completeTotalSales;
    }

    public int getCompleteWage() {
        return completeWage;
    }

    public void setCompleteWage(int completeWage) {
        this.completeWage = completeWage;
    }

    public int getCompleteBalance() {
        return completeBalance;
    }

    public void setCompleteBalance(int completeBalance) {
        this.completeBalance = completeBalance;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
