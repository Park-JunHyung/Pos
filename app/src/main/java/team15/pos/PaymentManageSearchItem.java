package team15.pos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentManageSearchItem extends LinearLayout
{

    private TextView payment_no;
    private TextView payment_price;
    private TextView payment_productList;
    private TextView payment_type;
    private TextView payment_date;


    public PaymentManageSearchItem(Context context)
    {
        super(context);
        init(context);
    }

    public PaymentManageSearchItem(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_payment_manage_search, this, true);

        payment_no = (TextView) findViewById(R.id.payment_no);
        payment_price = (TextView) findViewById(R.id.payment_price);
        payment_productList = (TextView) findViewById(R.id.payment_productList);
        payment_type = (TextView) findViewById(R.id.payment_type);
        payment_date = (TextView) findViewById(R.id.payment_date);
    }

    public void setPayment_no(String payment_no)
    {
        this.payment_no.setText(payment_no);
    }

    public void setPayment_price(String payment_price)
    {
        this.payment_price.setText(payment_price);
    }

    public void setPayment_productList(String payment_productList)
    {
        this.payment_productList.setText(payment_productList);
    }

    public void setPayment_type(String payment_type)
    {
        this.payment_type.setText(payment_type);
    }

    public void setPayment_date(String payment_date)
    {
        this.payment_date.setText(payment_date);
    }
}
