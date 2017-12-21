package team15.pos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team15.pos.dao.EmployeeAuth;
import team15.pos.dao.MemberDelete;
import team15.pos.dao.MemberEditSearch;
import team15.pos.dialog.EmployeeAuthDialog;
import team15.pos.dialog.MemberEditDialog;
import team15.pos.dto.Member;

public class CustomerDeleteActivity extends AppCompatActivity {


    private EditText deleteSearchPhoneNumber;
    private ListView searchListView;
    CustomerAdapter customerAdapter;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delete);

        ImageButton backBtn = (ImageButton)findViewById(R.id.backBtn);
        deleteSearchPhoneNumber = (EditText)findViewById(R.id.deleteSearchPhoneNumber);
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        searchListView = (ListView)findViewById(R.id.searchListView);
        deleteBtn = (Button)findViewById(R.id.deleteBtn);


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
                String searchPhone = deleteSearchPhoneNumber.getText().toString();
                //전화번호 검사
                /*
                String regex = "(01[016789])-(\\d{3,4})-\\d{4}$";
                Matcher matcher = Pattern.compile(regex).matcher(searchPhone);
                if (!matcher.find()) {
                    Toast.makeText(CustomerDeleteActivity.this, "전화번호 양식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    deleteSearchPhoneNumber.requestFocus();
                    deleteSearchPhoneNumber.setCursorVisible(true);
                    deleteSearchPhoneNumber.setSelection(searchPhone.length());
                    return;
                }
                */
                ArrayList<Member> members = new MemberDelete(CustomerDeleteActivity.this).search(searchPhone);

                if (members.size() == 0)
                {
                    Toast.makeText(CustomerDeleteActivity.this, "결과없음", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Member member : members)
                {
                    customerAdapter.addItem(member);
                }

                customerAdapter.notifyDataSetChanged();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences;
                SharedPreferences.Editor editor;
                int count=0;
                for (int i=0;i<customerAdapter.getCount();i++){
                    CustomerDeleteListItem customerDeleteListItem =(CustomerDeleteListItem) searchListView.getChildAt(i);
                    if(customerDeleteListItem.getSearchedDeleteCheckbox().isChecked()){
                        count++;

                        preferences = CustomerDeleteActivity.this.getSharedPreferences("data", Context.MODE_PRIVATE);
                        editor = preferences.edit();
                        Gson gson = new Gson();
                        String json;
                        json = preferences.getString("Temp", "");
                        ArrayList<Member> temp = gson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
                        if (null == temp)
                        {
                            temp = new ArrayList<>();
                        }
                        temp.add((Member) customerAdapter.getItem(i));
                        json = gson.toJson(temp);
                        editor.remove("Temp").commit();
                        editor.putString("Temp", json);
                        editor.commit();
                    }
                }
                if (count==0){
                    Toast.makeText(CustomerDeleteActivity.this, "선택한 대상이 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerDeleteActivity.this);
                builder.setTitle("삭제확인");
                builder.setMessage(count+"개를 삭제하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        EmployeeAuthDialog employeeAuthDialog = new EmployeeAuthDialog(CustomerDeleteActivity.this,1);
                        employeeAuthDialog.show();
                        customerAdapter.setItems(new ArrayList<Member>());
                        customerAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });


    }

    private class CustomerAdapter extends BaseAdapter
    {
        ArrayList<Member> items = new ArrayList<Member>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Member item){ items.add(item);}

        public void setItems(ArrayList<Member> items)
        {
            this.items = items;
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomerDeleteListItem view = new CustomerDeleteListItem(getApplicationContext());

            Member item = items.get(position);
            view.setMemberName(item.getMemberName());
            view.setMemberPhoneNumber(item.getMemberPhoneNumber());

            return view;
        }
    }
}
