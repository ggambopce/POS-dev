package stock;

import java.util.Date;

public class Stock {
    private int stockId;
    private int productId;
    private Date receivedDate;
    private Date expiryDate;
    private int quantity;        // 입고된 수량(구매시 유통기한지나지 않은 물건 차감)

    public Stock(int stockId, int productId, int quantity, Date expiryDate, Date receivedDate) {
        this.stockId = stockId;
        this.productId = productId;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.receivedDate = receivedDate;
    }


    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
