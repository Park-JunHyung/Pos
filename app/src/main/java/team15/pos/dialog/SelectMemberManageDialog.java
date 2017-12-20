package team15.pos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import team15.pos.R;

/**
 * Created by JSH on 2017-12-20.
 */

public class SelectMemberManageDialog extends Dialog {


    private Context context;

    public SelectMemberManageDialog(@NonNull Context context) {
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

        setContentView(R.layout.dialog_select_member_manage);

        Button addBtn = (Button) findViewById(R.id.select_member_add);
        Button editBtn = (Button) findViewById(R.id.select_member_edit);
        Button delBtn = (Button) findViewById(R.id.select_member_del);
        Button dismissBtn = (Button) findViewById(R.id.dismissBtnOfSelectMemberManage);


        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectMemberManageDialog.this.dismiss();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpDialog signUpDialog = new SignUpDialog(context);
                signUpDialog.show();
                SelectMemberManageDialog.this.dismiss();

            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberEditDialog memberEditDialog = new MemberEditDialog(context);
                memberEditDialog.show();
                SelectMemberManageDialog.this.dismiss();
            }
        });
    }
}
