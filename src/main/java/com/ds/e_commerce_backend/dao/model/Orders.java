package com.ds.e_commerce_backend.dao.model;
import java.util.Date;


/*

    @ 作用 :
     1. 對應 : 訂單 [ orders] 資料表


 */


public class Orders {

    private Integer orderId ;
    private Integer userId ;       // 訂單所屬使用者 id
    private Integer totalAmount ;  // 該次訂單交易費用總計

    private Date createdDate ;
    private Date lastModifiedDate ;


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
}
