package com.ds.e_commerce_backend.util.dto;

import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;


/*

 @ 轉換

   一次收集多個查詢請求參數


*/


public class ProductQueryParams {

    private ProductsCategory category ;  // 商品類別( Enum 類型 )
    private String search ;              // 關鍵字

    private String orderBy ;             // 欄位排序依據
    private String sort ;                // 升冪 或 降冪 排序



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


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
