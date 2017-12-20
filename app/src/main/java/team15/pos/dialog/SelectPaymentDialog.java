package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import team15.pos.R;

/**
 * Created by JSH on 2017-12-20.
 */

public class SelectPaymentDialog extends Dialog {

    private Context context;

    public SelectPaymentDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.select_payment);

        Button cashBtn = (Button)findViewById(R.id.select_payment_cash);
        Button cardBtn = (Button)findViewById(R.id.select_payment_card);
        Button pointBtn = (Button)findViewById(R.id.select_payment_point);
        Button dismissBtn = (Button)findViewById(R.id.dismissBtn);

        cashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "현금결제", Toast.LENGTH_SHORT).show();
                CashPaymentDialog cashPaymentDialog = new CashPaymentDialog(context);
                cashPaymentDialog.show();
                SelectPaymentDialog.this.dismiss();

            }
        });

        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "카드결제", Toast.LENGTH_SHORT).show();

                CardPaymentDialog cardPaymentDialog = new CardPaymentDialog(context);
                cardPaymentDialog.show();
                SelectPaymentDialog.this.dismiss();
            }
        });

        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "포인트결제", Toast.LENGTH_SHORT).show();
            }
        });

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPaymentDialog.this.dismiss();
            }
        });

    }





}
