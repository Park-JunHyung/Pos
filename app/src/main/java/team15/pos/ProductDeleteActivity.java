package team15.pos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dao.ProductDelete;
import team15.pos.dao.ProductEditSearch;
import team15.pos.dto.Product;

public class ProductDeleteActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    ArrayList<Product> productList=new ArrayList<>();
    ArrayList<String> categoryList=new ArrayList<>();
    ArrayList<Product> searchedList;
    ArrayList<Product> checkedList=new ArrayList<>();
    Button categoryBtn, searchBtn,delBtn;
    ImageButton backBtn;
    ListView listView;
    EditText searchNameText;
    CharSequence itemList[];
    SearchedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete);
        preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=preferences.edit();
        productList = gson.fromJson(preferences.getString("ProductList" , ""),new TypeToken<List<Product>>(){}.getType());
        categoryList= gson.fromJson(preferences.getString("Category" , ""),new TypeToken<List<String>>(){}.getType());


        delBtn= (Button) findViewById(R.id.productDelete_deleteBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Product> changedList=new ProductDelete().deleteProduct(checkedList,getApplicationContext());
                if (changedList!=null){
                    searchedList=new ProductEditSearch().searchProduct(changedList,categoryBtn.getText().toString(),searchNameText.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        backBtn= (ImageButton) findViewById(R.id.backBtn);
        categoryBtn= (Button) findViewById(R.id.productDelete_searchCategory);
        searchBtn= (Button) findViewById(R.id.productDelete_searchBtn);
        listView= (ListView) findViewById(R.id.productDeleteSearchList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
        searchNameText= (EditText) findViewById(R.id.productDelete_searchName);
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
                listView.setItemsCanFocus(false);
            }
        });
    }

    private void deleteCheckItem(int i) {
        int index=0;
        for (Product product:checkedList){
            if (product.getProductBarcode()==searchedList.get(i).getProductBarcode()){
                index=checkedList.indexOf(product);
            }
        }
        checkedList.remove(index);
    }

    private void saveCheckItem(int i) {
        boolean isDuplicate=false;
        for (Product product:checkedList){
            if (product.getProductBarcode()==searchedList.get(i).getProductBarcode()){
                isDuplicate=true;
            }
        }
        if (!isDuplicate){
            checkedList.add(searchedList.get(i));
        }
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
    private class SearchedAdapter extends BaseAdapter {

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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ProductDeleteSearchItem productDeleteSearchItem=new ProductDeleteSearchItem(getApplicationContext());
            productDeleteSearchItem.setProductName(searchedList.get(i).getProductName());
            productDeleteSearchItem.setProductBarcode(String.valueOf(searchedList.get(i).getProductBarcode()));
            for (Product product:checkedList){
                if (product.getProductBarcode()==searchedList.get(i).getProductBarcode()){
                    productDeleteSearchItem.setCheckBox(true);
                }
            }
            productDeleteSearchItem.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    Log.d("sibal : ", String.valueOf(i));
                    if (isChecked){
                        saveCheckItem(i);
                    }else {
                        deleteCheckItem(i);
                    }
                }
            });
            return productDeleteSearchItem;
        }
    }
}
