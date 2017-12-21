package team15.pos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import team15.pos.dto.Member;
import team15.pos.dto.Payment;

public class PaymentManageActivity extends AppCompatActivity
{

    ImageButton backBtn;
    Button setTransactionStartDate;
    Button setTransactionEndDate;
    Button searchAllTransactionBtn;

    Button goToCashTransactionBtn;
    Button goToCardTransactionBtn;

    ListView productSearchList;
    CustomerAdapter customerAdapter;
    Date startDate;
    Date endDate;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_manage);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        setTransactionStartDate = (Button) findViewById(R.id.setTransactionStartDate);
        setTransactionEndDate = (Button) findViewById(R.id.setTransactionEndDate);
        searchAllTransactionBtn = (Button) findViewById(R.id.searchAllTransactionBtn);

        goToCashTransactionBtn = (Button) findViewById(R.id.goToCashTransactionBtn);
        goToCardTransactionBtn = (Button) findViewById(R.id.goToCardTransactionBtn);

        productSearchList = (ListView) findViewById(R.id.productSearchList);
        customerAdapter = new CustomerAdapter();
        productSearchList.setAdapter(customerAdapter);

        Calendar calendar = Calendar.getInstance();

        setTransactionStartDate.setText(calendar.get(Calendar.YEAR) + "년 " + calendar.get(Calendar.MONTH) + "월 " + calendar.get(Calendar.DATE)+"일");
        setTransactionEndDate.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH)+1) + "월 " + calendar.get(Calendar.DATE)+"일");


        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        goToCashTransactionBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), CashPaymentManageActivity.class);
                startActivity(intent);
            }
        });
        goToCardTransactionBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), CardPaymentManageActivity.class);
                startActivity(intent);
            }
        });
        final DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener()
        {

            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String startDateString = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";
                setTransactionStartDate.setText(startDateString);
                try
                {
                    startDate = simpleDateFormat.parse(startDateString);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }

        };
        final DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener()
        {

            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String endDateString = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";
                setTransactionEndDate.setText(endDateString);
                try
                {
                    endDate = simpleDateFormat.parse(endDateString);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }

        };
        setTransactionStartDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(PaymentManageActivity.this, listener1,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) - 1,
                                calendar.get(Calendar.DATE)
                        );
                datePickerDialog.show();

            }
        });
        setTransactionEndDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(PaymentManageActivity.this, listener2,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );
                datePickerDialog.show();
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
            view.setPayment_productList(item.getPaymentProductList().toString());
            view.setPayment_type(item.getPaymentType());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");
            view.setPayment_date(simpleDateFormat.format(item.getPaymentDate()));

            return view;
        }
    }
}
