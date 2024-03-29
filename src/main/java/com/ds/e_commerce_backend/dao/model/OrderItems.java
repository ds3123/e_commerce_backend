package com.ds.e_commerce_backend.dao.model;


/*

    @ 作用 :
     1. 對應 : 訂單項目 [ order_items ] 資料表


 */

public class OrderItems {


    private Integer orderItemId ; // 訂單項目 id
    private Integer orderId ;     // 訂單項目 : 所屬訂單 id
    private Integer productId ;   // 訂單項目 : 對應商品(資訊) id
    private Integer quantity ;    // 訂單項目 : 數量
    private Integer amount ;      // 訂單項目 : 金額總計

    // 以下變數為因應需要 ( OrderItemsRowMapper ) , 後續追加 ( Products 類別也有 )
    private String productName ;  // 訂單項目 : 商品名稱
    private String imageUrl ;     // 訂單項目 : 商品圖片 URL



    // Getter & Setter
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
