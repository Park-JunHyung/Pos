package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team15.pos.R;
import team15.pos.dao.MemberSignUp;

/**
 * Created by JSH on 2017-12-20.
 */

public class SignUpDialog extends Dialog {


    private Context context;
    private boolean isCheck = false;
    private String name;
    private String phone;
    private EditText signUpPhoneInput;
    private EditText signUpIdInput;

    public SignUpDialog(@NonNull Context context) {
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

        setContentView(R.layout.dialog_customer_signup);

        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfSignUP);
        signUpIdInput = (EditText) findViewById(R.id.signUpIdInput);
        signUpPhoneInput = (EditText) findViewById(R.id.signUpPhoneInput);
        Button checkDuplication = (Button) findViewById(R.id.checkDuplication);
        Button saveMember = (Button) findViewById(R.id.saveMember);

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpDialog.this.dismiss();
            }
        });

        checkDuplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = signUpIdInput.getText().toString();
                phone = signUpPhoneInput.getText().toString();
                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(context, "입력받은 정보가 잘못되었습니다.", Toast.LENGTH_SHORT).show();

                } else {
                    String regex = "(01[016789])-(\\d{3,4})-\\d{4}$";
                    Matcher matcher = Pattern.compile(regex).matcher(phone);

                    if (!matcher.find()) {
                        Toast.makeText(context, "전화번호 양식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                        signUpPhoneInput.requestFocus();
                        signUpPhoneInput.setCursorVisible(true);
                        signUpPhoneInput.setSelection(phone.length());
                        return;
                    }

                    isCheck = new MemberSignUp().checkDuplicationFunc(name, phone);
                    if (isCheck) {
                        Toast.makeText(context, "중복확인 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "중복되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        saveMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
                    boolean success = new MemberSignUp().addMember(name, phone);
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    SignUpDialog.this.dismiss();
                } else {
                    Toast.makeText(context, "중복확인을 해주세요", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
