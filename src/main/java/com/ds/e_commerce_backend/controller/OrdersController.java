package com.ds.e_commerce_backend.controller;

import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.service.OrdersService;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid ;


@Validated
@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService ;


    // 新增 _ 訂單
    @PostMapping( "/users/{userId}/orders" )
    public ResponseEntity<?> createOrder(
                                          @PathVariable Integer userId ,
                                          @RequestBody @Valid CreateOrderRequest createOrderRequest
                                        ){

        // 新增訂單 id
        Integer orderId = ordersService.createOrder( userId , createOrderRequest ) ;

        // 取得該筆訂單資訊
        Orders order = ordersService.getOrderById( orderId ) ;


        return ResponseEntity.status( HttpStatus.CREATED ).body( order ) ;


    }


}
