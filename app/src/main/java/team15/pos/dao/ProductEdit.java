package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class ProductEdit {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public boolean addProduct(Product product, Context context){
        preferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(product);
        editor.remove("editProduct").commit();
        editor.putString("editProduct",json);
        editor.commit();
        return true;

    }
}
