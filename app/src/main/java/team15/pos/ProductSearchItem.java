package team15.pos;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductSearchItem extends LinearLayout {
    TextView productName,productBarcode;
    public ProductSearchItem(Context context) {
        super(context);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_product_search,this,true);
        productName=findViewById(R.id.searchedProductName);
        productBarcode=findViewById(R.id.searchedProductBarcode);
    }

    public void setProductName(String text) {
        productName.setText(text);
    }

    public void setProductBarcode(String text) {
        productBarcode.setText(text);
    }
}
