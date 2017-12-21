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

public class ProductAdd {

    ArrayList<Product> getProductList;
    ArrayList<String> category;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public boolean addProduct(Product getProduct, Context context){
        preferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        editor=preferences.edit();
        Gson gson=new Gson();
        String json;
        json=preferences.getString("ProductList","");
        getProductList = gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
        if (null == getProductList) {
            getProductList = new ArrayList<>();
        }
        getProductList.add(getProduct);
        json = gson.toJson(getProductList);
        editor.remove("ProductList").commit();
        editor.putString("ProductList", json);

        editor.commit();

        //카테고리추가
        json=preferences.getString("Category","");
        category=gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
        if (null == category) {
            category = new ArrayList<>();
        }
        if (!category.contains(getProduct.getProductCategory())){
            category.add(getProduct.getProductCategory());
            json = gson.toJson(category);
            editor.remove("Category").commit();
            editor.putString("Category", json);
            editor.commit();
        }

        return true;

    }
}
