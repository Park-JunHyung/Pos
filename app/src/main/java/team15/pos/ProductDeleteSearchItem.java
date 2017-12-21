package team15.pos;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductDeleteSearchItem extends LinearLayout {

    TextView productName,productBarcode;
    CheckBox checkBox;
    public ProductDeleteSearchItem(Context context) {
        super(context);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_product_delete_search,this,true);
        checkBox=findViewById(R.id.searchedDeleteCheckbox);
        checkBox.setChecked(false);
        productName=findViewById(R.id.searchedProductName);
        productBarcode=findViewById(R.id.searchedProductBarcode);
    }

    public void setProductName(String text) {
        productName.setText(text);
    }

    public void setProductBarcode(String text) {
        productBarcode.setText(text);
    }

    public void setCheckBox(boolean checked){
        checkBox.setChecked(checked);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
