package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team15.pos.R;
import team15.pos.dao.MemberAuth;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class UserAuthDialog extends Dialog {


    private Context context;
    private int listenerId;

    public UserAuthDialog(@NonNull Context context, int listenerId) {
        super(context);
        this.context = context;
        this.listenerId = listenerId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_user_auth);


        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfUserAuth);
        Button checkUser = (Button) findViewById(R.id.checkUserPhone);
        final EditText inputPhone=findViewById(R.id.userInputPhoneNumber);

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserAuthDialog.this.dismiss();
            }
        });

        View.OnClickListener[] listener = new View.OnClickListener[3];
        listener[0]=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member selectMember=new MemberAuth().memberAuthDone(inputPhone.getText().toString(),context);
                if (selectMember!=null){
                    PointPaymentDialog pointPaymentDialog = new PointPaymentDialog(context);
                    pointPaymentDialog.show();
                    UserAuthDialog.this.dismiss();
                }else {
                    Toast.makeText(context,"존재하지 않는 회원입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        };
        listener[1]=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member selectMember=new MemberAuth().memberAuthDone(inputPhone.getText().toString(),context);
                if (selectMember!=null) {
                    CardPaymentDialog cardPaymentDialog = new CardPaymentDialog(context);
                    cardPaymentDialog.show();
                    UserAuthDialog.this.dismiss();
                }else {
                    Toast.makeText(context,"존재하지 않는 회원입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        };
        checkUser.setOnClickListener(listener[listenerId]);
    }

}
