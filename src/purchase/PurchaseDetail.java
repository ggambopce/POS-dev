package purchase;

public class PurchaseDetail {
    int purchaseDetailId;       // 구매상세 id
    int purchaseQuantity;       // 구매수량 id
    int stockId;                // 어떤 입고 건에서 차감된 판매인지 추적

    // 외래키
    int purchaseId;
    int productId;

    public PurchaseDetail(int purchaseDetailId, int purchaseQuantity, int purchaseId, int productId, int stockId) {
        this.purchaseDetailId = purchaseDetailId;
        this.purchaseQuantity = purchaseQuantity;
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.stockId = stockId;
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

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }
}
