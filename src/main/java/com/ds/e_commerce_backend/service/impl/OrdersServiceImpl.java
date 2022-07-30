package com.ds.e_commerce_backend.service.impl;


import com.ds.e_commerce_backend.dao.OrdersDao;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.OrderItems;
import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.service.OrdersService;
import com.ds.e_commerce_backend.util.dto.orders.BuyItemRequest;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;
import com.ds.e_commerce_backend.util.dto.orders.OrderQueryParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao ;

    @Autowired
    private ProductsDao productsDao ;

    @Autowired
    private UsersDao usersDao ;

    // Log 紀錄
    private final static Logger log = LoggerFactory.getLogger( OrdersServiceImpl.class ) ;


    // --------------


    // 取得 _ 特定 ( id ) 訂單
    @Transactional
    @Override
    public Orders getOrderById( Integer orderId ) {

        // 取得 _ 特定 id 訂單
        Orders order = ordersDao.getOrderById( orderId ) ;

        // 取得 _ 特定 id 訂單，所包含商品項目
        List< OrderItems > orderItemsList = ordersDao.getOrderItemsByOrderId( orderId ) ;
        order.setOrderItemsList( orderItemsList ) ;

        return order ;

    }


    // 取得 _ 訂單列表
    @Override
    public List<Orders> getOrders( OrderQueryParams orderQueryParams ) {

        List< Orders > ordersList = ordersDao.getOrders( orderQueryParams ) ;

        for( Orders order : ordersList ){

            List< OrderItems > orderItemsList = ordersDao.getOrderItemsByOrderId( order.getOrderId() ) ;
            order.setOrderItemsList( orderItemsList ) ;

        }

        return ordersList ;

    }


    // 取得 _ 訂單商品項目總數
    @Override
    public Integer countOrder( OrderQueryParams orderQueryParams ) {

        return ordersDao.countOrder( orderQueryParams ) ;

    }

    // 新增 _ 訂單
    @Transactional
    @Override
    public Integer createOrder( Integer userId , CreateOrderRequest createOrderRequest) {

        // 檢查 _ User 是否存在
        Users user = usersDao.getUserById( userId ) ;

        if( user == null ){
           log.warn( "該 userId : {}，不存在" , userId ) ;
           throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ;
        }


        // 計算 _ 該筆訂單總計金額
        int totalAmount = 0 ;                                 // 訂單總額
        List<OrderItems> orderItemsList = new ArrayList<>();  // 訂單項目清單

        for( BuyItemRequest buyItemRequest : createOrderRequest.getBuyItemList() ){

            Products product = productsDao.getProductById( buyItemRequest.getProductId() ) ;

            // 檢查 _ 商品 :
            if( product == null ){                                     //  商品是否存在
                log.warn( "商品 ID : {}，不存在" , buyItemRequest.getProductId() ) ;
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ;
            }

            if( product.getStock() < buyItemRequest.getQuantity() ){  // 庫存是否足夠
                log.warn( "商品 ID : {}，庫存數量不足，無法購買 --> 剩餘庫存 : {} , 欲購買數量 : {} " ,
                          buyItemRequest.getProductId() , product.getStock() , buyItemRequest.getQuantity() ) ;
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ;
            }

            // 扣除 _ 商品庫存
            productsDao.updateStock( product.getProductId() , product.getStock() - buyItemRequest.getQuantity() ) ;


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




        // -------------

        // 建立 _ 該筆訂單 ( 資料表 : [ orders ] )
        Integer orderId = ordersDao.createOrder( userId , totalAmount ) ;

        // 新增 _ 訂單項目 ( 資料表 : [ order_items ] )
        ordersDao.createOrderItems( orderId , orderItemsList ) ;

        return orderId ;

    }


}

