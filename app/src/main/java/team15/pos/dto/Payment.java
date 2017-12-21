package team15.pos.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Payment {
    private String paymentNumber;
    private int paymentPrice;
    //    private ArrayList<Product> paymentProductList;
    private HashMap<String, ArrayList<Product>> arrayListMap=new HashMap<>();
    private String paymentType;
    private Date paymentDate;

    public Payment(String paymentNumber, int paymentPrice, ArrayList<Product> paymentProductList, String paymentType, Date paymentDate) {
        this.paymentNumber = paymentNumber;
        this.paymentPrice = paymentPrice;
        this.arrayListMap.put("t", paymentProductList);
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public ArrayList<Product> getPaymentProductList() {
        return arrayListMap.get("t");
    }

    public void setArrayListMap(ArrayList<Product> arrayListMap) {
        this.arrayListMap.put("t", arrayListMap);
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
