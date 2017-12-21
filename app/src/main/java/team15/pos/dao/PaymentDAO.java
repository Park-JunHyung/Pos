package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team15.pos.dto.Payment;
import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class PaymentDAO {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void createPayment(int getPayment,String type,Context context) {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        System.currentTimeMillis();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

        Gson gson=new Gson();
        String json;
        json=preferences.getString("paymentProductList","");
        String paymentNumber= format1.format(date);
        int paymentPrice=getPayment;
        ArrayList<Product> paymentProductList= gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
        String paymentType=type;
        Date paymentDate=date;

        String p1=preferences.getString("PaymentList","");

        ArrayList<Payment> paymentList=gson.fromJson(p1,new TypeToken<ArrayList<Payment>>(){}.getType());
        if (paymentList==null){
            paymentList=new ArrayList<>();
        }
        Payment payment=new Payment(paymentNumber,paymentPrice,paymentProductList,paymentType,paymentDate);
        paymentList.add(payment);
        String p = gson.toJson(paymentList);
        editor.remove("PaymentList").commit();
        editor.putString("PaymentList", p);
        editor.commit();


        json=preferences.getString("ProductList","");
        ArrayList<Product> productList= gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
        for (Product paymentProduct:paymentProductList){
            for (Product product:productList){
                if (product.getProductBarcode()==paymentProduct.getProductBarcode()){
                    int pastAmount=product.getProductAmount()-paymentProduct.getProductAmount();
                    product.setProductAmount(pastAmount);
                }
            }
        }

        String p2 = gson.toJson(productList);
        editor.remove("ProductList").commit();
        editor.putString("ProductList", p2);
        editor.commit();
    }
}
