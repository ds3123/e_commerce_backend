package com.ds.e_commerce_backend.util;


/*

  @ 處理 _ 資料分頁功能

*/

import java.util.List;

public class Pagination< T >{

    private Integer limit ;   // 每次取得資料數
    private Integer offset ;  // 每隔多少筆
    private Integer total ;   // 資料總筆數
    private List<T> results ; // 回傳資料


    // Getter & Setter ----------------

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

}
