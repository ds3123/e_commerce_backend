package com.ds.e_commerce_backend.service.impl;


import com.ds.e_commerce_backend.dao.OrdersDao;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.dao.model.OrderItems;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.service.OrdersService;
import com.ds.e_commerce_backend.util.dto.orders.BuyItemRequest;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao ;

    @Autowired
    private ProductsDao productsDao ;


    // 新增 _ 訂單
    @Transactional
    @Override
    public Integer createOrder( Integer userId , CreateOrderRequest createOrderRequest) {

        // 計算 _ 該筆訂單總計金額
        int totalAmount = 0 ;                                 // 訂單總額
        List<OrderItems> orderItemsList = new ArrayList<>();  // 訂單項目清單

        for( BuyItemRequest buyItemRequest : createOrderRequest.getBuyItemList() ){

            Products product = productsDao.getProductById( buyItemRequest.getProductId() ) ;

            // 計算總價錢
            int amount  = buyItemRequest.getQuantity() * product.getPrice() ;
            totalAmount = totalAmount + amount ;

            // 轉換 _ ButItem 為 OrderItem
            OrderItems orderItems = new OrderItems() ;
            orderItems.setProductId( buyItemRequest.getProductId() ) ;
            orderItems.setQuantity( buyItemRequest.getQuantity() ) ;
            orderItems.setAmount( amount );

            orderItemsList.add( orderItems ) ;

        }

        // 建立 _ 該筆訂單 ( 資料表 : [ orders ] )
        Integer orderId = ordersDao.createOrder( userId , totalAmount ) ;

        // 新增 _ 訂單項目 ( 資料表 : [ order_items ] )
        ordersDao.createOrderItems( orderId , orderItemsList ) ;

        return orderId ;

    }


}

