package product;

import java.util.Date;

public class Product {
    int productId;
    String productName;
    String manufacturer;
    Date expiryDate;
    char adultOnly;
    int price;
    Date receivedDate;
    int stock;


    public Product(int productId, String productName, String manufacturer, Date expiryDate, char adultOnly, int price, Date receivedDate, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.adultOnly = adultOnly;
        this.price = price;
        this.receivedDate = receivedDate;
        this.stock = stock;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public char getAdultOnly() {
        return adultOnly;
    }

    public void setAdultOnly(char adultOnly) {
        this.adultOnly = adultOnly;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
