package com.ds.e_commerce_backend.util.dto.orders;


/*

 @ 資料轉換 : 統一收集、處理 _ 多個 ( 訂單 ) 查詢請求參數


*/

public class OrderQueryParams {


    private Integer userId ;   // 帳號 / 使用者 id

    // 分頁 ( Pagination ) ~ 效能：避免一次取得所有資料
    private Integer limit ;    // 每次取得資料數
    private Integer offset ;   // 每隔多少筆

    // Getter & Setter ----------------
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
