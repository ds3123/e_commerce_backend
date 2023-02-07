package com.ds.e_commerce_backend.util.dto.products;


import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;

/*

 @ 資料轉換 : 統一收集、處理 _ 多個 ( 商品 ) 查詢請求：參數

*/


public class ProductQueryParams {

    // 查詢條件 ( Filtering )
    private ProductsCategory category ;  // 商品類別( Enum 類型 )
    private String search ;              // 關鍵字

    // 排序 ( Sorting )
    private String orderBy ;             // 欄位排序依據
    private String sort ;                // 升冪 或 降冪 排序

    // 分頁 ( Pagination ) ~ 效能：避免一次取得所有資料
    private Integer limit ;              // 每次取得資料數
    private Integer offset ;             // 每隔多少筆



    // Getter & Setter ----------------

    public ProductsCategory getCategory() {
        return category;
    }

    public void setCategory( ProductsCategory category ) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch( String search ) {
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
