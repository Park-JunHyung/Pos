package team15.pos;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerEditListItem extends LinearLayout
{
    private TextView memberName;
    private TextView memberPhoneNumber;

    public CustomerEditListItem(Context context) {
        super(context);
        init(context);
    }
    public CustomerEditListItem(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_customer_edit, this, true);

        memberName = (TextView)findViewById(R.id.memberName);
        memberPhoneNumber = (TextView)findViewById(R.id.memberPhoneNumber);
    }

    public void setMemberName(String memberName)
    {
        this.memberName.setText(memberName);
    }

    public void setMemberPhoneNumber(String memberPhoneNumber)
    {
        this.memberPhoneNumber.setText(memberPhoneNumber);
    }
}
