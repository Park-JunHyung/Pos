package team15.pos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CashOrCardPaymentListItem extends LinearLayout
{
    private TextView cashOrCardPayment_no;
    private TextView cashOrCardPayment_price;
    private TextView cashOrCardPayment_productList;
    private TextView cashOrCardPayment_date;


    public CashOrCardPaymentListItem(Context context)
    {
        super(context);
        init(context);
    }

    public CashOrCardPaymentListItem(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_cash_or_card_payment, this, true);

        cashOrCardPayment_no = (TextView) findViewById(R.id.payment_no);
        cashOrCardPayment_price = (TextView) findViewById(R.id.payment_price);
        cashOrCardPayment_productList = (TextView) findViewById(R.id.payment_productList);
        cashOrCardPayment_date = (TextView) findViewById(R.id.payment_date);
    }

    public void setPayment_no(String payment_no)
    {
        this.cashOrCardPayment_no.setText(payment_no);
    }

    public void setPayment_price(String payment_price)
    {
        this.cashOrCardPayment_price.setText(payment_price);
    }

    public void setPayment_productList(String payment_productList)
    {
        this.cashOrCardPayment_productList.setText(payment_productList);
    }

    public void setPayment_date(String payment_date)
    {
        this.cashOrCardPayment_date.setText(payment_date);
    }
}
