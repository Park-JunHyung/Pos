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

public class POSDAO
{

    ArrayList<Product> getProductList;
    ArrayList<String> category;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public POSDAO(Context context)
    {
        this.context = context;
    }

    public Product searchProductUseBarcord(int barcord)
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("ProductList", "");
        getProductList = gson.fromJson(json, new TypeToken<List<Product>>()
        {
        }.getType());
        if (null == getProductList)
        {
            getProductList = new ArrayList<>();
        }

        for (Product product : getProductList)
        {
            if (product.getProductBarcode() == barcord)
            {
                return product;
            }
        }

        return null;

    }
}
