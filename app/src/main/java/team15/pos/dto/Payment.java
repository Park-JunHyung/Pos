package team15.pos.dto;

import java.util.ArrayList;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Payment {
    private String paymentNumber;
    private int paymentPrice;
    private ArrayList<Product> paymentProductList;
    private String paymentType;
    private String paymentDate;

    public Payment(String paymentNumber, int paymentPrice, ArrayList<Product> paymentProductList, String paymentType, String paymentDate) {
        this.paymentNumber = paymentNumber;
        this.paymentPrice = paymentPrice;
        this.paymentProductList = paymentProductList;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public ArrayList<Product> getPaymentProductList() {
        return paymentProductList;
    }

    public void setPaymentProductList(ArrayList<Product> paymentProductList) {
        this.paymentProductList = paymentProductList;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
