package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Payment;
import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class ProductRefundProcess {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ProductRefundProcess(Context context)
    {
        this.context = context;
    }

    public boolean process(Payment payment)
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        Gson gson = new Gson();
        String json = preferences.getString("PaymentList", "");

        ArrayList<Payment> paymentList = gson.fromJson(json, new TypeToken<ArrayList<Payment>>()
        {
        }.getType());
        if (paymentList == null)
        {
            paymentList = new ArrayList<>();
        }

        ArrayList<Payment> filterList = new ArrayList<>(paymentList);
        for (Payment temp : paymentList)
        {
            if (temp.getPaymentNumber().equals(payment.getPaymentNumber())){
                filterList.remove(temp);
            }
        }
        editor.remove("PaymentList").commit();
        String tmp = gson.toJson(filterList);
        editor.putString("PaymentList", tmp);
        editor.commit();

        json=preferences.getString("ProductList","");
        ArrayList<Product> productList= gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());

        for (Product paymentProduct:payment.getPaymentProductList()){
            for (Product product:productList){
                if (product.getProductBarcode()==paymentProduct.getProductBarcode()){
                    int pastAmount=product.getProductAmount()+paymentProduct.getProductAmount();
                    product.setProductAmount(pastAmount);
                }
            }
        }

        String p2 = gson.toJson(productList);
        editor.remove("ProductList").commit();
        editor.putString("ProductList", p2);
        editor.commit();

        return true;
    }
}
