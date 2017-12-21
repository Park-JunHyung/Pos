package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import team15.pos.MainActivity;
import team15.pos.R;
import team15.pos.dao.PointPayment;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class PointPaymentDialog extends Dialog {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    private Context context;

    public PointPaymentDialog(@NonNull Context context) {
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

        setContentView(R.layout.dialog_point_payment);


        Gson gson = new Gson();
        String json;
        json = preferences.getString("authMember", "");

        Member authMember= gson.fromJson(json, new TypeToken<Member>() {}.getType());
        final int getTotalPrice=preferences.getInt("totalPrice",0);

        Button dismissBtn =findViewById(R.id.dismissBtnOfPoint);
        Button pointCheck =findViewById(R.id.pointCheck);
        TextView curPoint=findViewById(R.id.currentPoint);
        TextView usingPoint=findViewById(R.id.totalPriceOfPointDialog);
        TextView leftPoint=findViewById(R.id.leftPoint);

        final int curP=authMember.getMemberPoint();
        int leftP=curP-getTotalPrice;
        curPoint.setText(String.valueOf(curP));
        usingPoint.setText(String.valueOf(getTotalPrice));
        leftPoint.setText(String.valueOf(leftP));

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PointPaymentDialog.this.dismiss();
            }
        });
        pointCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curP<getTotalPrice){
                    Toast.makeText(context,"포인트가 부족합니다.",Toast.LENGTH_SHORT).show();
                }else {
                    new PointPayment().setTypeAndPayment(getTotalPrice,context);
                    PointPaymentDialog.this.dismiss();
                    ((MainActivity)context).resetListView();
                }
            }
        });
    }
}
