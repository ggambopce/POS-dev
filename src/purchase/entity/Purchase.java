package purchase.entity;

import java.util.Date;

public class Purchase {
    int purchaseId;
    Date purchaseTime;
    int totalAmount;            // 총 결제 금액

    public Purchase(int purchaseId, Date purchaseTime, int totalAmount) {
        this.purchaseId = purchaseId;
        this.purchaseTime = purchaseTime;
        this.totalAmount = totalAmount;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
