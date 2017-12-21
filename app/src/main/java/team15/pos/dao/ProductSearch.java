package team15.pos.dao;

import java.util.ArrayList;

import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class ProductSearch {
    ArrayList<Product> searchedList=new ArrayList<>();
    public ArrayList<Product> searchProduct(ArrayList<Product> productList, String category, String findProduct) {

        if (category.equals("모두")){
            for (Product product:productList){
                if (product.getProductName().contains(findProduct)){
                    searchedList.add(product);
                }
            }
        }else{
            for (Product product : productList) {
                if (product.getProductCategory().equals(category)&&product.getProductName().contains(findProduct)) {
                    searchedList.add(product);
                }
            }
        }
        return searchedList;
    }
}
