package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import team15.pos.MainActivity;
import team15.pos.R;
import team15.pos.dao.CardPayment;

/**
 * Created by JSH on 2017-12-20.
 */

public class CardPaymentDialog extends Dialog {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private Context context;

    public CardPaymentDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_card_payment);
        int getTotalPrice=preferences.getInt("totalPrice",0);


        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfCard);

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardPaymentDialog.this.dismiss();
            }
        });

        try {
            Thread.sleep(2000);
            new CardPayment().setTypeAndPayment(getTotalPrice,context);
            Toast.makeText(context,"카드 결제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
            CardPaymentDialog.this.dismiss();
            ((MainActivity)context).resetListView();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
