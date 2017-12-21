package team15.pos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
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

import team15.pos.dao.ProductEditSearch;
import team15.pos.dto.Product;

public class ProductSearchActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    ArrayList<Product> productList=new ArrayList<>();
    ArrayList<String> categoryList=new ArrayList<>();
    ArrayList<Product> searchedList;
    Button categoryBtn, searchBtn;
    ImageButton backBtn;
    ListView listView;
    EditText searchNameText;
    CharSequence itemList[];
    SearchedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=preferences.edit();
        productList = gson.fromJson(preferences.getString("ProductList" , ""),new TypeToken<List<Product>>(){}.getType());
        categoryList= gson.fromJson(preferences.getString("Category" , ""),new TypeToken<List<String>>(){}.getType());
        if (productList==null){
            productList=new ArrayList<>();
        }
        backBtn= (ImageButton) findViewById(R.id.backBtn);
        categoryBtn= (Button) findViewById(R.id.product_searchCategory);
        searchBtn= (Button) findViewById(R.id.product_searchBtn);
        listView= (ListView) findViewById(R.id.productSearchList);
        searchNameText= (EditText) findViewById(R.id.product_searchName);
        adapter=new SearchedAdapter();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategory();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchedList=new ProductEditSearch().searchProduct(productList,categoryBtn.getText().toString(),searchNameText.getText().toString());
                if (searchedList.size()==0){
                    Toast.makeText(getApplicationContext(),"일치하는 항목이 없습니다.",Toast.LENGTH_SHORT).show();
                }
                listView.setAdapter(adapter);
            }
        });
    }


    private void setCategory() {
        if (categoryList!=null){
            itemList=new CharSequence[categoryList.size()+1];
            int i=1;
            itemList[0]="모두";
            for (String item:categoryList){
                CharSequence cs=new StringBuffer(item);
                itemList[i++]=cs;
            }
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("카테고리를 선택하세요.");
        builder.setItems(itemList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                categoryBtn.setText(itemList[i].toString());
                dialogInterface.dismiss();
            }

        });
        builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
        }else {
            Toast.makeText(getApplicationContext(),"카테고리가 없습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    private class SearchedAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return searchedList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ProductSearchItem productSearchItem=new ProductSearchItem(getApplicationContext());
            productSearchItem.setProductName(searchedList.get(i).getProductName());
            productSearchItem.setProductBarcode(String.valueOf(searchedList.get(i).getProductBarcode()));
            return productSearchItem;
        }
    }
}
