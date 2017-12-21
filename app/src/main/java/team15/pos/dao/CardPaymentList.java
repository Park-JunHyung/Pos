package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;

import team15.pos.dto.Payment;

/**
 * Created by JSH on 2017-12-20.
 */

public class CardPaymentList {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CardPaymentList(Context context)
    {
        this.context = context;
    }

    public ArrayList<Payment> getList(Date startDate, Date endDate)
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

        ArrayList<Payment> filterList = new ArrayList<>();
        for (Payment payment : paymentList)
        {
            if (startDate.before(payment.getPaymentDate())&&
                    endDate.after(payment.getPaymentDate())&&
                    payment.getPaymentType().equals("카드")){
                filterList.add(payment);
            }
        }


        return filterList;
    }
}
