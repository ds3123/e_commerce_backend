package com.ds.e_commerce_backend.service;

import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;
import com.ds.e_commerce_backend.util.dto.orders.OrderQueryParams;
import java.util.List;


public interface OrdersService {

    // 取得 _ 特定 ( id ) 訂單
    Orders getOrderById( Integer orderId ) ;

    // 取得 _ 訂單列表
    List< Orders > getOrders( OrderQueryParams orderQueryParams ) ;

    // 取得 _ 訂單商品項目總數
    Integer countOrder( OrderQueryParams orderQueryParams ) ;

    // 新增 _ 訂單
    Integer createOrder( Integer userId , CreateOrderRequest createOrderRequest ) ;


}
