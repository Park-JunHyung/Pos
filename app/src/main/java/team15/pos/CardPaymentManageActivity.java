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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import team15.pos.dto.Payment;

public class CardPaymentManageActivity extends AppCompatActivity {

    ImageButton backBtn;
    Button setCardTransactionStartDate;
    Button setCardTransactionEndDate;
    Button searchCardTransactionBtn;

    ListView cardPaymentList;
    CustomerAdapter customerAdapter;

    Date startDate;
    Date endDate;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment_manage);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        setCardTransactionStartDate = (Button) findViewById(R.id.setCardTransactionStartDate);
        setCardTransactionEndDate = (Button) findViewById(R.id.setCardTransactionEndDate);
        searchCardTransactionBtn = (Button) findViewById(R.id.searchCardTransactionBtn);

        cardPaymentList = (ListView) findViewById(R.id.cardPaymentList);

        customerAdapter = new CustomerAdapter();
        cardPaymentList.setAdapter(customerAdapter);

        Calendar calendar = Calendar.getInstance();

        setCardTransactionStartDate.setText(calendar.get(Calendar.YEAR) + "년 " + calendar.get(Calendar.MONTH) + "월 " + calendar.get(Calendar.DATE)+"일");
        setCardTransactionEndDate.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH)+1) + "월 " + calendar.get(Calendar.DATE)+"일");


        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        final DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener()
        {

            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String startDateString = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";
                setCardTransactionStartDate.setText(startDateString);
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
                setCardTransactionEndDate.setText(endDateString);
                try
                {
                    endDate = simpleDateFormat.parse(endDateString);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }

        };
        setCardTransactionStartDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(CardPaymentManageActivity.this, listener1,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) - 1,
                                calendar.get(Calendar.DATE)
                        );
                datePickerDialog.show();

            }
        });
        setCardTransactionEndDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(CardPaymentManageActivity.this, listener2,
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
            CashOrCardPaymentListItem view = new CashOrCardPaymentListItem(getApplicationContext());

            Payment item = items.get(position);

            view.setPayment_no(item.getPaymentNumber());
            view.setPayment_price(String.valueOf(item.getPaymentPrice()));
            view.setPayment_productList(item.getPaymentProductList().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");
            view.setPayment_date(simpleDateFormat.format(item.getPaymentDate()));
            return view;
        }
    }
}
