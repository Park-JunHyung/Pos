package team15.pos.dao;

import android.content.Context;

/**
 * Created by JSH on 2017-12-20.
 */

public class CashPayment {

    public void setTypeAndPayment(int getPayment, Context context){
        new PaymentDAO().createPayment(getPayment,"현금",context);
    }

}
