package com.ds.e_commerce_backend.service;


import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;

public interface OrdersService {

    // 新增 _ 訂單
    Integer createOrder( Integer userId , CreateOrderRequest createOrderRequest ) ;



}
