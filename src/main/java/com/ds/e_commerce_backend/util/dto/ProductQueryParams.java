package com.ds.e_commerce_backend.util.dto;

import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;


/*

 @ 轉換

   一次收集多個查詢請求參數


*/


public class ProductQueryParams {

    private ProductsCategory category ;  // 商品類別( Enum 類型 )
    private String search ;              // 關鍵字



    // Getter & Setter
    public ProductsCategory getCategory() {
        return category;
    }

    public void setCategory(ProductsCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


}
