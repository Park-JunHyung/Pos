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
import team15.pos.dao.EmployeeAuth;
import team15.pos.dto.Manager;

/**
 * Created by JSH on 2017-12-20.
 */

public class EmployeeAuthDialog extends Dialog {


    private Context context;
    private String id;
    private String password;
    private int loginCount;

    private EditText employeeIdInput;
    private EditText employeePasswordInput;

    public EmployeeAuthDialog(@NonNull Context context) {
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

        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfEmployeeAuth);
        employeeIdInput = (EditText) findViewById(R.id.employeeIdInput);
        employeePasswordInput = (EditText) findViewById(R.id.employeePasswordInput);
        Button checkEmployee = (Button) findViewById(R.id.checkEmployee);

        loginCount = 0;

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployeeAuthDialog.this.dismiss();
            }
        });


        checkEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = employeeIdInput.getText().toString();
                password = employeePasswordInput.getText().toString();
                boolean success = new EmployeeAuth().employeeAuth(id, password);
                loginCount++;
                if (success) {
                    SelectMemberManageDialog selectMemberManageDialog = new SelectMemberManageDialog(context);
                    selectMemberManageDialog.show();
                    EmployeeAuthDialog.this.dismiss();
                } else {
                    if (loginCount > 10) {
                        ManagerAuthDialog managerAuthDialog = new ManagerAuthDialog(context);
                        managerAuthDialog.show();
                        EmployeeAuthDialog.this.dismiss();
                    } else {
                        Toast.makeText(context, "비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
