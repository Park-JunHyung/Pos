package team15.pos.dto;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Product {
    private String productName;
    private int productAmount;
    private int productPrice;
    private String productCompany;
    private String productExpireDate;
    private String productPosition;
    private String productCategory;
    private int productBarcode;

    public Product(String productName, int productAmount, int productPrice, String productCompany, String productExpireDate, String productPosition, String productCategory, int productBarcode) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.productCompany = productCompany;
        this.productExpireDate = productExpireDate;
        this.productPosition = productPosition;
        this.productCategory = productCategory;
        this.productBarcode = productBarcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public String getProductExpireDate() {
        return productExpireDate;
    }

    public void setProductExpireDate(String productExpireDate) {
        this.productExpireDate = productExpireDate;
    }

    public String getProductPosition() {
        return productPosition;
    }

    public void setProductPosition(String productPosition) {
        this.productPosition = productPosition;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(int productBarcode) {
        this.productBarcode = productBarcode;
    }
}
