package model;

public class PurchaseDetail {
    int purchaseDetailId;       // 구매상세 id
    int purchaseQuantity;       // 구매수량 id

    // 외래키
    int purchaseId;
    int productId;

    public PurchaseDetail(int purchaseDetailId, int purchaseQuantity, int purchaseId, int productId) {
        this.purchaseDetailId = purchaseDetailId;
        this.purchaseQuantity = purchaseQuantity;
        this.purchaseId = purchaseId;
        this.productId = productId;
    }

    public int getPurchaseDetailId() {
        return purchaseDetailId;
    }

    public void setPurchaseDetailId(int purchaseDetailId) {
        this.purchaseDetailId = purchaseDetailId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
