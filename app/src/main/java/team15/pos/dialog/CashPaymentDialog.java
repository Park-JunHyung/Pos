package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team15.pos.R;
import team15.pos.dao.CashPayment;

/**
 * Created by JSH on 2017-12-20.
 */

public class CashPaymentDialog extends Dialog {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int getPayment,getTotalPrice,getChange;
    boolean isOk=false;
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

        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        final EditText payment = findViewById(R.id.payment);
        final TextView totalPrice = findViewById(R.id.totalPriceOfDialog);
        final TextView change = findViewById(R.id.change);
        Button dismissBtn = findViewById(R.id.dismissBtnOfCash);
        Button cashCheck = findViewById(R.id.cashCheck);
        getTotalPrice=preferences.getInt("totalPrice",0);

        totalPrice.setText(String.valueOf(getTotalPrice));
        payment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getPayment= Integer.parseInt(payment.getText().toString());
                totalPrice.setText(String.valueOf(getTotalPrice));
                getChange=getPayment-getTotalPrice;
                if (getChange<0){
                    Toast.makeText(context,"받은 금액이 모자랍니다.",Toast.LENGTH_SHORT).show();
                    isOk=false;
                }else {
                    change.setText(String.valueOf(getChange));
                    isOk=true;
                }
            }
        });
        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CashPaymentDialog.this.dismiss();
            }
        });
        cashCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOk){
                    new CashPayment().setTypeAndPayment(getTotalPrice,context);
                    Toast.makeText(context,"현금 결제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"받은 금액이 모자랍니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
