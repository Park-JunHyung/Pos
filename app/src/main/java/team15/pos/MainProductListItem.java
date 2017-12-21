package team15.pos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainProductListItem extends LinearLayout
{
    private TextView productName;
    private TextView productAmount;
    private TextView productPrice;

    public MainProductListItem(Context context)
    {
        super(context);
        init(context);
    }

    public MainProductListItem(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_main_product, this, true);

        productName = (TextView) findViewById(R.id.productName);
        productAmount = (TextView) findViewById(R.id.productAmount);
        productPrice = (TextView) findViewById(R.id.productPrice);
    }

    public void setProductName(String productName)
    {
        this.productName.setText(productName);
    }

    public void setProductAmount(String productAmount)
    {
        this.productAmount.setText(productAmount);
    }

    public void setProductPrice(String productPrice)
    {
        this.productPrice.setText(productPrice);
    }
}
