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

import team15.pos.R;
import team15.pos.dao.EmployeeAuth;

/**
 * Created by JSH on 2017-12-21.
 */

public class ManagerAuthDialog extends Dialog {


    private Context context;
    private String id;
    private String password;

    public ManagerAuthDialog(@NonNull Context context) {
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

        setContentView(R.layout.dialog_employee_auth);
        TextView title = (TextView) findViewById(R.id.authTitle);
        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfEmployeeAuth);
        EditText employeeIdInput = (EditText) findViewById(R.id.employeeIdInput);
        EditText employeePasswordInput = (EditText) findViewById(R.id.employeePasswordInput);
        Button checkEmployee = (Button) findViewById(R.id.checkEmployee);

        title.setText("관리자 인증");

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagerAuthDialog.this.dismiss();
            }
        });

        id = employeeIdInput.getText().toString();
        password = employeePasswordInput.getText().toString();

        checkEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = new EmployeeAuth().employeeAuth(id, password);

                if (success) {
                    SelectMemberManageDialog selectMemberManageDialog = new SelectMemberManageDialog(context);
                    selectMemberManageDialog.show();
                    ManagerAuthDialog.this.dismiss();
                } else {

                    Toast.makeText(context, "입력에 오류가 있습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
