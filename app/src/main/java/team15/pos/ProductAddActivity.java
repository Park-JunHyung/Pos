package team15.pos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import team15.pos.dao.ProductAdd;
import team15.pos.dao.ProductEdit;
import team15.pos.dto.Product;

public class ProductAddActivity extends AppCompatActivity {
    EditText editProductName,editProductAmount,editProductPrice,editProductCompany,editProductExpireDate,editProductPosition,editProductCategory,editProductBarcode;
    Button addBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Product product;
    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=preferences.edit();

        String json;
        json=preferences.getString("editProduct","");
        product = gson.fromJson(json,new TypeToken<Product>(){}.getType());

        editProductName= (EditText) findViewById(R.id.addProductName);
        editProductAmount= (EditText) findViewById(R.id.addProductAmount);
        editProductPrice= (EditText) findViewById(R.id.addProductPrice);
        editProductCompany= (EditText) findViewById(R.id.addProductCompany);
        editProductExpireDate= (EditText) findViewById(R.id.addProductExpireDate);
        editProductPosition= (EditText) findViewById(R.id.addProductPosition);
        editProductCategory= (EditText) findViewById(R.id.addProductCategory);
        editProductBarcode= (EditText) findViewById(R.id.addProductBarcode);
        addBtn= (Button) findViewById(R.id.addProductBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (omitCheck()){
                    Toast.makeText(getApplicationContext(),"입력하지 않은 항목이 있습니다.",Toast.LENGTH_SHORT).show();
                }else if (maxAmount()){
                    Toast.makeText(getApplicationContext(),"입력할 수 있는 최대 수량을 초과하였습니다.",Toast.LENGTH_SHORT).show();
                }else if (addProduct()){
                    if (product!=null){
                        Toast.makeText(getApplicationContext(),"기존의 물품이 수정되었습니다.",Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                    }else {
                        Toast.makeText(getApplicationContext(),"새로운 물품등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"에러.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        if (product!=null){
            editProductName.setText(product.getProductName());
            editProductAmount.setText(String.valueOf(product.getProductAmount()));
            editProductPrice.setText(String.valueOf(product.getProductPrice()));
            editProductCompany.setText(product.getProductCompany());
            editProductExpireDate.setText(product.getProductExpireDate());
            editProductPosition.setText(product.getProductPosition());
            editProductCategory.setText(product.getProductCategory());
            editProductBarcode.setText(String.valueOf(product.getProductBarcode()));
        }
    }

    private boolean maxAmount() {
        if (Integer.valueOf(editProductAmount.getText().toString())>=999){
            return true;
        }else
            return false;
    }

    private boolean addProduct() {
        Product newProduct=new Product(editProductName.getText().toString(),Integer.valueOf(editProductAmount.getText().toString()),Integer.valueOf(editProductPrice.getText().toString()),editProductCompany.getText().toString(),
                editProductExpireDate.getText().toString(),editProductPosition.getText().toString(),editProductCategory.getText().toString(),Integer.valueOf(editProductBarcode.getText().toString()));
        if (product!=null){
            new ProductEdit().editProduct(product,newProduct,getApplicationContext());
            return true;
        }else {
            return new ProductAdd().addProduct(newProduct,getApplicationContext());
        }
    }

    private boolean omitCheck() {
        if (editProductName.getText().toString().isEmpty()||editProductAmount.getText().toString().isEmpty()||editProductPrice.getText().toString().isEmpty()
                ||editProductCompany.getText().toString().isEmpty()||editProductExpireDate.getText().toString().isEmpty()||editProductPosition.getText().toString().isEmpty()
                ||editProductCategory.getText().toString().isEmpty()||editProductBarcode.getText().toString().isEmpty())
            return true;
        else
            return false;
    }

    @Override
    protected void onStop() {
        super.onStop();

        editor.remove("editProduct").commit();
        editor.putString("editProduct","");
        editor.commit();
    }
}
