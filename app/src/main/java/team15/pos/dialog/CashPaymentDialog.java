package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import team15.pos.R;

/**
 * Created by JSH on 2017-12-20.
 */

public class CashPaymentDialog extends Dialog {


    private Context context;

    public CashPaymentDialog(@NonNull Context context) {
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

        setContentView(R.layout.dialog_cash_payment);

        TextView payment = (TextView)findViewById(R.id.payment);
        EditText totalPrice = (EditText)findViewById(R.id.totalPriceOfDialog);
        TextView change = (TextView)findViewById(R.id.change);
        Button dismissBtn = (Button)findViewById(R.id.dismissBtnOfCash);
        Button cashCheck = (Button)findViewById(R.id.cashCheck);

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CashPaymentDialog.this.dismiss();
            }
        });
        cashCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CashPaymentDialog.this.dismiss();
            }
        });
    }
}
