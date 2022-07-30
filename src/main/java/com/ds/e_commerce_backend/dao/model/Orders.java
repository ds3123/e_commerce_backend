package com.ds.e_commerce_backend.dao.model;
import java.util.Date;
import java.util.List;


/*

    @ 作用 :
     1. 對應 : 訂單 [ orders] 資料表


 */


public class Orders {

    private Integer orderId ;
    private Integer userId ;                     // 訂單所屬使用者 id
    private Integer totalAmount ;                // 該次訂單交易費用總計

    private Date createdDate ;
    private Date lastModifiedDate ;

    // 以下變數為因應需要 , 後續追加
    private List< OrderItems > orderItemsList ;  // 訂單所包含商品項目



    // Getter & Setter

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }


}
