package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class ProductEdit {
    ArrayList<Product> getProductList;
    ArrayList<String> category;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public void addProduct(Product product, Context context){
        preferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(product);
        editor.remove("editProduct").commit();
        editor.putString("editProduct",json);
        editor.commit();
    }

    public void editProduct(Product curProduct, Product newProduct,Context context){
        preferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        editor=preferences.edit();
        Gson gson=new Gson();
        String json;
        json=preferences.getString("ProductList","");
        getProductList = gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
        int index=0;
        for (Product product:getProductList){
            if (product.getProductBarcode()==curProduct.getProductBarcode()&&product.getProductName().equals(curProduct.getProductName())){
                index=getProductList.indexOf(product);
            }
        }

        getProductList.set(index,newProduct);
        json=gson.toJson(getProductList);
        editor.remove("ProductList").commit();
        editor.putString("ProductList",json);
        editor.commit();

        json=preferences.getString("Category","");
        category=gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
        if (null == category) {
            category = new ArrayList<>();
        }
        if (!category.contains(newProduct.getProductCategory())){
            category.add(newProduct.getProductCategory());
            json = gson.toJson(category);
            editor.remove("Category").commit();
            editor.putString("Category", json);
            editor.commit();
        }
    }
}
