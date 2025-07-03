package model;

import java.util.Date;

public class Purchase {
    int purchaseId;
    Date purchaseTime;

    public Purchase(int purchaseId, Date purchaseTime) {
        this.purchaseId = purchaseId;
        this.purchaseTime = purchaseTime;
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
}
