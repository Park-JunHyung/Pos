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
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team15.pos.CustomerEditActivity;
import team15.pos.R;
import team15.pos.dao.MemberEdit;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberEditDialog extends Dialog
{

    private Context context;
    private Member member;
    private EditText memberNameInput;
    private EditText memberPhoneInput;
    private TextView memberPointInput;

    public MemberEditDialog(@NonNull Context context, Member member)
    {
        super(context);
        this.context = context;
        this.member = member;
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

        setContentView(R.layout.dialog_member_edit);

        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfMemberEdit);
        memberNameInput = (EditText) findViewById(R.id.memberNameInput);
        memberPhoneInput = (EditText) findViewById(R.id.memberPhoneInput);
        memberPointInput = (TextView) findViewById(R.id.memberPointInput);
        Button saveEditMember = (Button) findViewById(R.id.saveEditMember);

        memberNameInput.setText(member.getMemberName());
        memberPhoneInput.setText(member.getMemberPhoneNumber());
        memberPointInput.setText(String.valueOf(member.getMemberPoint()));


        dismissBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MemberEditDialog.this.dismiss();

            }
        });

        saveEditMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newName = memberNameInput.getText().toString();
                String newPhone = memberPhoneInput.getText().toString();
                if (newName.isEmpty() || newPhone.isEmpty())
                {
                    Toast.makeText(context, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //전화번호 검사
                String regex = "(01[016789])-(\\d{3,4})-\\d{4}$";
                Matcher matcher = Pattern.compile(regex).matcher(newPhone);
                if (!matcher.find()) {
                    Toast.makeText(context, "전화번호 양식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    memberPhoneInput.requestFocus();
                    memberPhoneInput.setCursorVisible(true);
                    memberPhoneInput.setSelection(newPhone.length());
                    return;
                }
                Member newMember = new Member(newName, newPhone);
                newMember.setMemberPoint(Integer.valueOf(memberPointInput.getText().toString()));
                boolean success = new MemberEdit(context).memberEdit(member, newMember);
                if (success)
                {
                    MemberEditDialog.this.dismiss();
                }
            }
        });

    }
}
