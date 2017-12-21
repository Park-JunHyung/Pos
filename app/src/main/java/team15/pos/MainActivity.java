package team15.pos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dialog.SelectMemberManageDialog;
import team15.pos.dialog.SelectPaymentDialog;
import team15.pos.dialog.SelectProductManageDialog;
import team15.pos.dto.Employee;
import team15.pos.dto.Member;
import team15.pos.dto.POS;
import team15.pos.dto.Payment;
import team15.pos.dto.Product;

public class MainActivity extends AppCompatActivity {
    POS pos;
    ArrayList<Member> memberList=new ArrayList<>();
    ArrayList<Product> productList=new ArrayList<>();
    ArrayList<Employee> employeeList=new ArrayList<>();
    ArrayList<Payment> paymentList=new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runPaymentBtn = (Button)findViewById(R.id.runPayment);
        Button productManagementBtn = (Button)findViewById(R.id.productManagementBtn);
        Button productRefundBtn = (Button)findViewById(R.id.productRefundBtn);
        Button customManagementBtn = (Button)findViewById(R.id.customManagementBtn);
        Button productSearchBtn= (Button) findViewById(R.id.productSearchBtn);
        Button paymentManageBtn= (Button) findViewById(R.id.paymentManageBtn);
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
        productRefundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), PaymentRefundActivity.class);
                startActivity(intent);
//                CardRefundDIalog cardRefundDIalog = new CardRefundDIalog(MainActivity.this);
//                cardRefundDIalog.show();
            }
        });
        productSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ProductSearchActivity.class);
                startActivity(intent);
            }
        });
        customManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectMemberManageDialog selectMemberManageDialog = new SelectMemberManageDialog(MainActivity.this);
                selectMemberManageDialog.show();
            }
        });
        paymentManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), PaymentManageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //값가져오기
        preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=preferences.edit();
        productList = gson.fromJson(preferences.getString("ProductList" , ""),new TypeToken<List<Product>>(){}.getType());

        pos=new POS(memberList,productList,employeeList,paymentList,10000);

    }
}
