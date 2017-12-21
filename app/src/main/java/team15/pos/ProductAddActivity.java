package team15.pos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team15.pos.dao.ProductAdd;
import team15.pos.dto.Product;

public class ProductAddActivity extends AppCompatActivity {
    EditText editProductName,editProductAmount,editProductPrice,editProductCompany,editProductExpireDate,editProductPosition,editProductCategory,editProductBarcode;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
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
                }else if (addProduct()){
                    Toast.makeText(getApplicationContext(),"새로운 물품등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"에러.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean addProduct() {
        Product newProduct=new Product(editProductName.getText().toString(),Integer.valueOf(editProductAmount.getText().toString()),Integer.valueOf(editProductPrice.getText().toString()),editProductCompany.getText().toString(),
                editProductExpireDate.getText().toString(),editProductPosition.getText().toString(),editProductCategory.getText().toString(),Integer.valueOf(editProductBarcode.getText().toString()));
        return new ProductAdd().addProduct(newProduct,getApplicationContext());
    }

    private boolean omitCheck() {
        if (editProductName.getText().toString().isEmpty()||editProductAmount.getText().toString().isEmpty()||editProductPrice.getText().toString().isEmpty()
                ||editProductCompany.getText().toString().isEmpty()||editProductExpireDate.getText().toString().isEmpty()||editProductPosition.getText().toString().isEmpty()
                ||editProductCategory.getText().toString().isEmpty()||editProductBarcode.getText().toString().isEmpty())
            return true;
        else
            return false;
    }
}
