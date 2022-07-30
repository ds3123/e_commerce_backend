package com.ds.e_commerce_backend.service;


import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;

public interface OrdersService {


    // 取得 _ 特定 ( id ) 訂單
    Orders getOrderById( Integer orderId ) ;

    // 新增 _ 訂單
    Integer createOrder( Integer userId , CreateOrderRequest createOrderRequest ) ;


}
