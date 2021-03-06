package team15.pos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dao.POSDAO;
import team15.pos.dialog.EmployeeAuthDialog;
import team15.pos.dialog.SelectPaymentDialog;
import team15.pos.dialog.SelectProductManageDialog;
import team15.pos.dto.Employee;
import team15.pos.dto.Member;
import team15.pos.dto.POS;
import team15.pos.dto.Payment;
import team15.pos.dto.Product;

public class MainActivity extends AppCompatActivity
{
    POS pos;
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Employee> employeeList = new ArrayList<>();
    ArrayList<Payment> paymentList = new ArrayList<>();
    int balance;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    ListView shoppingBasket;
    CustomerAdapter customerAdapter;
    EditText barcodeNumber;
    TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        Button runPaymentBtn = (Button) findViewById(R.id.runPayment);
        Button productManagementBtn = (Button) findViewById(R.id.productManagementBtn);
        Button productRefundBtn = (Button) findViewById(R.id.productRefundBtn);
        Button customManagementBtn = (Button) findViewById(R.id.customManagementBtn);
        Button productSearchBtn = (Button) findViewById(R.id.productSearchBtn);
        Button paymentManageBtn = (Button) findViewById(R.id.paymentManageBtn);
        Button addBarcodeNumber = (Button) findViewById(R.id.addBarcodeNumber);
        barcodeNumber =(EditText)findViewById(R.id.barcodeNumber);
        totalPrice =(TextView)findViewById(R.id.totalPrice);
        shoppingBasket = (ListView)findViewById(R.id.shoppingBasket);

        customerAdapter = new CustomerAdapter();
        shoppingBasket.setAdapter(customerAdapter);

        runPaymentBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (customerAdapter.getCount()==0){
                    Toast.makeText(getApplicationContext(),"상품을 추가해주세요.",Toast.LENGTH_SHORT).show();
                }else {
                    editor.putInt("totalPrice", Integer.parseInt(totalPrice.getText().toString()));
                    editor.commit();

                    String json = gson.toJson(customerAdapter.getItems());
                    editor.remove("paymentProductList").commit();
                    editor.putString("paymentProductList", json);
                    editor.commit();
                    SelectPaymentDialog selectPaymentDialog = new SelectPaymentDialog(MainActivity.this);
                    selectPaymentDialog.show();
                }
            }
        });
        productManagementBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EmployeeAuthDialog employeeAuthDialog = new EmployeeAuthDialog(MainActivity.this, 2);
                employeeAuthDialog.show();
                /*
                SelectProductManageDialog selectProductManageDialog = new SelectProductManageDialog(MainActivity.this);
                selectProductManageDialog.show();
                */
            }
        });
        productRefundBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), PaymentRefundActivity.class);
                startActivity(intent);
//                CardRefundDIalog cardRefundDIalog = new CardRefundDIalog(MainActivity.this);
//                cardRefundDIalog.show();
            }
        });
        productSearchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ProductSearchActivity.class);
                startActivity(intent);
            }
        });
        customManagementBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EmployeeAuthDialog employeeAuthDialog = new EmployeeAuthDialog(MainActivity.this, 0);
                employeeAuthDialog.show();
                /*
                SelectMemberManageDialog selectMemberManageDialog = new SelectMemberManageDialog(MainActivity.this);
                selectMemberManageDialog.show();
                */
            }
        });
        paymentManageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), PaymentManageActivity.class);
                startActivity(intent);
            }
        });
        addBarcodeNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (barcodeNumber.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"바코드를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else {
                    Product product =
                            new POSDAO(MainActivity.this)
                                    .searchProductUseBarcord(Integer.valueOf(barcodeNumber.getText().toString()));
                    if (product!=null){
                        customerAdapter.addItem(product);
                        customerAdapter.notifyDataSetChanged();
                        int total = Integer.valueOf(totalPrice.getText().toString());
                        totalPrice.setText(String.valueOf(total+product.getProductPrice()));
                    }else {
                        Toast.makeText(getApplicationContext(),"해당 물품이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        productList = gson.fromJson(preferences.getString("ProductList", ""), new TypeToken<List<Product>>()
        {
        }.getType());
        balance=preferences.getInt("balance",10000);
        pos = new POS(memberList, productList, employeeList, paymentList, balance);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //값가져오기

        editor.putInt("balance", balance);
        editor.commit();

    }
    private class CustomerAdapter extends BaseAdapter
    {
        public ArrayList<Product> getItems() {
            return items;
        }

        public void setItems(ArrayList<Product> items) {
            this.items = items;
        }

        ArrayList<Product> items = new ArrayList<Product>();

        @Override
        public int getCount()
        {
            return items.size();
        }

        public void addItem(Product item)
        {
            for (Product product : items)
            {
                if (product.getProductBarcode()==item.getProductBarcode()){
                    product.setProductAmount(product.getProductAmount()+1);
                    notifyDataSetChanged();
                    return;
                }
            }
            item.setProductAmount(1);
            items.add(item);
        }

        @Override
        public Object getItem(int position)
        {
            return items.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            MainProductListItem view = new MainProductListItem(getApplicationContext());

            Product item = items.get(position);
            view.setProductName(item.getProductName());
            view.setProductAmount(String.valueOf(item.getProductAmount()));
            view.setProductPrice(String.valueOf(item.getProductPrice()));

            return view;
        }
    }
    public void resetListView(){
        customerAdapter.setItems(new ArrayList<Product>());
        customerAdapter.notifyDataSetChanged();
        totalPrice.setText("0");
        barcodeNumber.setText("");
    }
}
