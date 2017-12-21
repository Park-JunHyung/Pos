package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JSH on 2017-12-20.
 */

public class CashPayment {

    public void setTypeAndPayment(int getPayment, Context context){
        new PaymentDAO().createPayment(getPayment,"현금",context);


        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=preferences.edit();
        int balance=preferences.getInt("balance",0);
        editor.putInt("balance", balance+getPayment);
        editor.commit();
    }

}
