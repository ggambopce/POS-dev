package stock;

import java.util.Date;

public class Stock {
    private int stockId;
    private int productId;
    private Date receivedDate;
    private Date expiryDate;
    private int quantity;

    public Stock(int stockId, int productId, Date receivedDate, Date expiryDate, int quantity) {
        this.stockId = stockId;
        this.productId = productId;
        this.receivedDate = receivedDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
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
