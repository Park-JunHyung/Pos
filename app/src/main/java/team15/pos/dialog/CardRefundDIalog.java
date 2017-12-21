package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import team15.pos.R;
import team15.pos.dao.ProductRefundProcess;
import team15.pos.dto.Payment;

/**
 * Created by JSH on 2017-12-20.
 */

public class CardRefundDIalog extends Dialog
{


    private Context context;
    private Payment payment;

    public CardRefundDIalog(@NonNull Context context)
    {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_card_refund);

        TextView title = (TextView) findViewById(R.id.title);
        TextView message = (TextView) findViewById(R.id.message);

        if (payment.getPaymentType().equals("카드"))
        {
            title.setText("카드 환불");
            message.setText("카드정보 읽는 중...");
        } else if (payment.getPaymentType().equals("현금"))
        {
            title.setText("현금 환불");
            message.setText("현금 환불 중...");
        }

        boolean success = new ProductRefundProcess(context).process(payment);
        if (success){
            Toast.makeText(context, "환불 성공", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "환불 실패", Toast.LENGTH_SHORT).show();
        }
    }

    public Payment getPayment()
    {
        return payment;
    }

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }
}
