package team15.pos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import team15.pos.dao.ProductRefund;
import team15.pos.dialog.CardRefundDIalog;
import team15.pos.dto.Payment;
import team15.pos.dto.Product;

public class PaymentRefundActivity extends AppCompatActivity
{
    ListView productRefundSearchList;
    EditText productRefund_number;
    CustomerAdapter customerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_refund);

        ImageButton backFromRefundProduct = (ImageButton) findViewById(R.id.backFromRefundProduct);
        productRefund_number = (EditText) findViewById(R.id.productRefund_number);
        Button productRefund_searchBtn = (Button) findViewById(R.id.productRefund_searchBtn);
        productRefundSearchList = (ListView) findViewById(R.id.productRefundSearchList);

        customerAdapter = new CustomerAdapter();
        productRefundSearchList.setAdapter(customerAdapter);

        backFromRefundProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        productRefund_searchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String paymentid = productRefund_number.getText().toString();
                if (paymentid.isEmpty()){
                    Toast.makeText(PaymentRefundActivity.this, "결제번호를 입력헤주세요", Toast.LENGTH_SHORT).show();
                }
                ArrayList<Payment> payments = new ProductRefund(PaymentRefundActivity.this).getList(paymentid);
                System.out.println("test");

                if (payments.size()==0){
                    Toast.makeText(PaymentRefundActivity.this, "결과없음", Toast.LENGTH_SHORT).show();
                }
                for(Payment payment:payments){
                    customerAdapter.addItem(payment);
                    customerAdapter.notifyDataSetChanged();
                }
            }
        });
        productRefundSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Payment payment = (Payment) customerAdapter.getItem(position);
                CardRefundDIalog cardRefundDIalog = new CardRefundDIalog(PaymentRefundActivity.this);
                cardRefundDIalog.setPayment(payment);
                cardRefundDIalog.show();
            }
        });


    }
    private class CustomerAdapter extends BaseAdapter
    {
        ArrayList<Payment> items = new ArrayList<Payment>();

        @Override
        public int getCount()
        {
            return items.size();
        }

        public void addItem(Payment item)
        {
            items.add(item);
        }

        public void setItems(ArrayList<Payment> items)
        {
            this.items = items;
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
            PaymentManageSearchItem view = new PaymentManageSearchItem(getApplicationContext());

            Payment item = items.get(position);
            view.setPayment_no(item.getPaymentNumber());
            view.setPayment_price(String.valueOf(item.getPaymentPrice()));

            String productlist = "";
            for (Product product : item.getPaymentProductList())
            {
                productlist = productlist + product.getProductName() + " " + product.getProductAmount() + ", ";
            }

            productlist = productlist.substring(0, productlist.length() - 2);

            view.setPayment_productList(productlist);
            view.setPayment_type(item.getPaymentType());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");
            view.setPayment_date(simpleDateFormat.format(item.getPaymentDate()));

            return view;
        }
    }

}
