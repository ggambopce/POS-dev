package product;

import java.util.Date;

public class Product {
    int productId;
    String productName;
    String manufacturer;
    char adultOnly;
    int price;
    int stock;              // 유통기한 안 지난 재고 수량

    public Product(int productId, String productName, String manufacturer, char adultOnly, int price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.adultOnly = adultOnly;
        this.price = price;
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

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}
}
