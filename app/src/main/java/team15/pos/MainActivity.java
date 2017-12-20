package team15.pos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team15.pos.dialog.SelectPaymentDialog;
import team15.pos.dialog.SelectProductManageDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runPaymentBtn = (Button)findViewById(R.id.runPayment);
        Button productManagementBtn = (Button)findViewById(R.id.productManagementBtn);

        runPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SelectPaymentDialog selectPaymentDialog = new SelectPaymentDialog(MainActivity.this);

               selectPaymentDialog.show();
            }
        });
        productManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectProductManageDialog selectProductManageDialog = new SelectProductManageDialog(MainActivity.this);
                selectProductManageDialog.show();
            }
        });
    }
}
