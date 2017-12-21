package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class ProductDelete {
    ArrayList<Product> getProductList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public ArrayList<Product> deleteProduct(ArrayList<Product> deleteList,Context context){
        if (deleteList.size()==0){
            Toast.makeText(context,"선택된 물품이 없습니다.",Toast.LENGTH_SHORT).show();
            return null;
        }else {
            ArrayList<Integer> indexList=new ArrayList<>();
            preferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            editor=preferences.edit();
            Gson gson=new Gson();
            String json=preferences.getString("ProductList","");
            getProductList = gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
            for (Product product:getProductList){
                for (Product delProduct:deleteList){
                    if (product.getProductBarcode()==delProduct.getProductBarcode()){
                        indexList.add(getProductList.indexOf(product));
                    }
                }
            }
            for (Integer index:indexList){
                getProductList.remove((int)index);
            }
            json=gson.toJson(getProductList);
            editor.remove("ProductList").commit();
            editor.putString("ProductList",json);
            editor.commit();
            Toast.makeText(context,"해당 물품이 정상적으로 삭제되었습니다.",Toast.LENGTH_SHORT).show();
        }
        return getProductList;
    }
}
