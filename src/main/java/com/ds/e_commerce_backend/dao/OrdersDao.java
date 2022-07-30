package com.ds.e_commerce_backend.dao;

import com.ds.e_commerce_backend.dao.model.OrderItems;
import com.ds.e_commerce_backend.dao.model.Orders;

import java.util.List;

public interface OrdersDao {


    // 取得 _ 特定 ( id ) 訂單
    Orders getOrderById( Integer orderId ) ;


    // 取得 _ 特定 id 訂單，所包含商品項目
    List< OrderItems > getOrderItemsByOrderId( Integer orderId ) ;


    // 建立 _ 該筆訂單
    Integer createOrder( Integer userId , Integer totalAmount ) ;


    // 新增 _ 訂單項目
    void createOrderItems( Integer orderId , List< OrderItems > orderItemsList )  ;


}
