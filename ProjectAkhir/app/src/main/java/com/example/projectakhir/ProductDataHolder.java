package com.example.projectakhir;

import java.util.ArrayList;

public class ProductDataHolder {
    private static ArrayList <ProductModel> productList;
    public static ArrayList <ProductModel> getProductList() {
        return productList;
    }

    public static void setProductList(ArrayList<ProductModel> productList) {
        ProductDataHolder.productList = productList;
    }
}
