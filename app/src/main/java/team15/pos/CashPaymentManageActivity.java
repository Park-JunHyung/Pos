package team15.pos;

import android.app.DatePickerDialog;
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

import team15.pos.dao.CardPaymentList;
import team15.pos.dao.CashPaymentList;
import team15.pos.dto.Payment;
import team15.pos.dto.Product;

public class CashPaymentManageActivity extends AppCompatActivity {


    ImageButton backBtn;
    Button setCashTransactionStartDate;
    Button setCashTransactionEndDate;
    Button searchCashTransactionBtn;

    ListView cashPaymentList;
    CustomerAdapter customerAdapter;

    Date startDate;
    Date endDate;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    DatePickerDialog.OnDateSetListener listener2;
    DatePickerDialog.OnDateSetListener listener1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_payment_manage);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        setCashTransactionStartDate = (Button) findViewById(R.id.setCashTransactionStartDate);
        setCashTransactionEndDate = (Button) findViewById(R.id.setCashTransactionEndDate);
        searchCashTransactionBtn = (Button) findViewById(R.id.searchCashTransactionBtn);

        cashPaymentList = (ListView) findViewById(R.id.cashPaymentList);

        customerAdapter = new CustomerAdapter();
        cashPaymentList.setAdapter(customerAdapter);

        Calendar calendar = Calendar.getInstance();

        setCashTransactionStartDate.setText(calendar.get(Calendar.YEAR) + "년 " + calendar.get(Calendar.MONTH) + "월 " + calendar.get(Calendar.DATE)+"일");
        setCashTransactionEndDate.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH)+1) + "월 " + calendar.get(Calendar.DATE)+"일");

        try
        {
            startDate = simpleDateFormat.parse(calendar.get(Calendar.YEAR) + "년 " + calendar.get(Calendar.MONTH) + "월 " + calendar.get(Calendar.DATE) + "일");
            endDate = simpleDateFormat.parse(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 " + (calendar.get(Calendar.DATE) + 1) + "일");
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        listener1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String startDateString = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";
                try
                {
                    Calendar calendar = Calendar.getInstance();
                    Date date = simpleDateFormat.parse(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 " + (calendar.get(Calendar.DATE)) + "일");

                    if (date.before(simpleDateFormat.parse(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일")))
                    {
                        Toast.makeText(CashPaymentManageActivity.this, "잘못된 날짜 입니다.", Toast.LENGTH_SHORT).show();

                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(CashPaymentManageActivity.this, listener1,
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH) - 1,
                                        calendar.get(Calendar.DATE)
                                );
                        datePickerDialog.show();

                    }

                    setCashTransactionStartDate.setText(startDateString);

                    startDateString = year + "년 " + (monthOfYear) + "월 " + (dayOfMonth + 1) + "일";
                    startDate = simpleDateFormat.parse(startDateString);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        };


        listener2 = new DatePickerDialog.OnDateSetListener()
        {

            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String endDateString = year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일";
                try
                {
                    Calendar calendar = Calendar.getInstance();
                    Date date = simpleDateFormat.parse(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 " + (calendar.get(Calendar.DATE)) + "일");

                    if (date.before(simpleDateFormat.parse(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일")))
                    {
                        Toast.makeText(CashPaymentManageActivity.this, "잘못된 날짜 입니다.", Toast.LENGTH_SHORT).show();

                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(CashPaymentManageActivity.this, listener2,
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DATE)
                                );
                        datePickerDialog.show();

                    }

                    setCashTransactionEndDate.setText(endDateString);

                    endDateString = year + "년 " + (monthOfYear + 1) + "월 " + (dayOfMonth + 1) + "일";
                    endDate = simpleDateFormat.parse(endDateString);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        };
        setCashTransactionStartDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(CashPaymentManageActivity.this, listener1,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) - 1,
                                calendar.get(Calendar.DATE)
                        );
                datePickerDialog.show();

            }
        });
        setCashTransactionEndDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(CashPaymentManageActivity.this, listener2,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );
                datePickerDialog.show();
            }
        });
        searchCashTransactionBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (startDate.after(endDate))
                {
                    Toast.makeText(CashPaymentManageActivity.this, "잘못된 날짜입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                customerAdapter.setItems(new ArrayList<Payment>());
                customerAdapter.notifyDataSetChanged();

                ArrayList<Payment> payments = new CashPaymentList(CashPaymentManageActivity.this).getList(startDate, endDate);

                if (payments.size() == 0)
                {
                    Toast.makeText(CashPaymentManageActivity.this, "결과 없음", Toast.LENGTH_SHORT).show();
                }

                for (Payment payment : payments)
                {
                    customerAdapter.addItem(payment);
                    customerAdapter.notifyDataSetChanged();
                }
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
            CashOrCardPaymentListItem view = new CashOrCardPaymentListItem(getApplicationContext());

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

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");
            view.setPayment_date(simpleDateFormat.format(item.getPaymentDate()));
            return view;
        }
    }
}
