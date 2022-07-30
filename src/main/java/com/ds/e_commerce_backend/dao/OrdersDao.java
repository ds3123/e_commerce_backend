package com.ds.e_commerce_backend.dao;

import com.ds.e_commerce_backend.dao.model.OrderItems;

import java.util.List;

public interface OrdersDao {


    // 建立 _ 該筆訂單
    Integer createOrder( Integer userId , Integer totalAmount ) ;


    // 新增 _ 訂單項目
    void createOrderItems( Integer orderId , List< OrderItems > orderItemsList )  ;


}
