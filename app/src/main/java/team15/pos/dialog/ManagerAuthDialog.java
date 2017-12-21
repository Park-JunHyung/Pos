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

import java.util.ArrayList;

import team15.pos.R;
import team15.pos.dao.EmployeeAuth;
import team15.pos.dao.MemberDelete;
import team15.pos.dao.MemberDeleteAuth;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-21.
 */

public class ManagerAuthDialog extends Dialog
{


    private Context context;
    private String id;
    private String password;
    private EditText employeeIdInput;
    private EditText employeePasswordInput;
    private int listenerid;

    public ManagerAuthDialog(@NonNull Context context, int listenerid)
    {
        super(context);
        this.context = context;
        this.listenerid = listenerid;
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

        setContentView(R.layout.dialog_employee_auth);
        TextView title = (TextView) findViewById(R.id.authTitle);
        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfEmployeeAuth);
        employeeIdInput = (EditText) findViewById(R.id.employeeIdInput);
        employeePasswordInput = (EditText) findViewById(R.id.employeePasswordInput);
        Button checkEmployee = (Button) findViewById(R.id.checkEmployee);

        title.setText("관리자 인증");

        dismissBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ManagerAuthDialog.this.dismiss();
            }
        });
        View.OnClickListener[] listener = new View.OnClickListener[3];
        listener[0] = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                id = employeeIdInput.getText().toString();
                password = employeePasswordInput.getText().toString();

                boolean success = new EmployeeAuth(context).managerAuth(id, password);

                if (success)
                {
                    SelectMemberManageDialog selectMemberManageDialog = new SelectMemberManageDialog(context);
                    selectMemberManageDialog.show();

                    ManagerAuthDialog.this.dismiss();
                } else
                {

                    Toast.makeText(context, "입력에 오류가 있습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        };
        listener[1] = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                id = employeeIdInput.getText().toString();
                password = employeePasswordInput.getText().toString();

                boolean success = new MemberDeleteAuth(context).managerAuth(id, password);

                if (success)
                {
                    new MemberDelete(context).delete();
                    Toast.makeText(context, "삭제", Toast.LENGTH_SHORT).show();
                    ManagerAuthDialog.this.dismiss();
                } else
                {

                    Toast.makeText(context, "입력에 오류가 있습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        };
        checkEmployee.setOnClickListener(listener[listenerid]);
    }
}
