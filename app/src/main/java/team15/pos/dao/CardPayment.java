package team15.pos.dao;

import android.content.Context;

/**
 * Created by JSH on 2017-12-20.
 */

public class CardPayment {

    public void setTypeAndPayment(int getPayment, Context context){
        new PaymentDAO().createPayment(getPayment,"카드",context);
    }
}
