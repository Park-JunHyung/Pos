package team15.pos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team15.pos.dao.MemberEdit;
import team15.pos.dao.MemberEditSearch;
import team15.pos.dialog.MemberEditDialog;
import team15.pos.dto.Member;

public class CustomerEditActivity extends AppCompatActivity
{

    private EditText editSearchPhoneNumber;
    private ListView searchListView;
    CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        editSearchPhoneNumber = (EditText) findViewById(R.id.editSearchPhoneNumber);
        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchListView = (ListView) findViewById(R.id.searchListView);


        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customerAdapter = new CustomerAdapter();
                searchListView.setAdapter(customerAdapter);
                String searchPhone = editSearchPhoneNumber.getText().toString();
                //전화번호 검사
                /*
                String regex = "(01[016789])-(\\d{3,4})-\\d{4}$";
                Matcher matcher = Pattern.compile(regex).matcher(searchPhone);
                if (!matcher.find()) {
                    Toast.makeText(CustomerEditActivity.this, "전화번호 양식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    editSearchPhoneNumber.requestFocus();
                    editSearchPhoneNumber.setCursorVisible(true);
                    editSearchPhoneNumber.setSelection(searchPhone.length());
                    return;
                }
                */
                ArrayList<Member> members = new MemberEditSearch(CustomerEditActivity.this).search(searchPhone);

                if (members.size() == 0)
                {
                    Toast.makeText(CustomerEditActivity.this, "결과없음", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Member member : members)
                {
                    customerAdapter.addItem(member);
                }
                customerAdapter.notifyDataSetChanged();
            }
        });

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Member member = (Member) customerAdapter.getItem(position);

                MemberEditDialog memberEditDialog = new MemberEditDialog(CustomerEditActivity.this, member);
                memberEditDialog.show();
            }
        });


    }

    private class CustomerAdapter extends BaseAdapter
    {
        ArrayList<Member> items = new ArrayList<Member>();

        @Override
        public int getCount()
        {
            return items.size();
        }

        public void addItem(Member item)
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
            CustomerEditListItem view = new CustomerEditListItem(getApplicationContext());

            Member item = items.get(position);
            view.setMemberName(item.getMemberName());
            view.setMemberPhoneNumber(item.getMemberPhoneNumber());

            return view;
        }
    }
}
