package team15.pos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerDeleteListItem extends LinearLayout
{

    private TextView memberName;
    private TextView memberPhoneNumber;
    private CheckBox searchedDeleteCheckbox;
    public CustomerDeleteListItem(Context context) {
        super(context);
        init(context);
    }
    public CustomerDeleteListItem(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_customer_delete, this, true);

        memberName = (TextView)findViewById(R.id.memberName);
        memberPhoneNumber = (TextView)findViewById(R.id.memberPhoneNumber);
        searchedDeleteCheckbox = (CheckBox)findViewById(R.id.searchedDeleteCheckbox);
    }

    public void setMemberName(String memberName)
    {
        this.memberName.setText(memberName);
    }

    public void setMemberPhoneNumber(String memberPhoneNumber)
    {
        this.memberPhoneNumber.setText(memberPhoneNumber);
    }

    public void setSearchedDeleteCheckbox(boolean searchedDeleteCheckbox)
    {
        this.searchedDeleteCheckbox.setChecked(searchedDeleteCheckbox);
    }

    public CheckBox getSearchedDeleteCheckbox()
    {
        return searchedDeleteCheckbox;
    }
}
