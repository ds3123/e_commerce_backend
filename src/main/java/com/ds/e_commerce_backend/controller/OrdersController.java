package com.ds.e_commerce_backend.controller;

import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.service.OrdersService;
import com.ds.e_commerce_backend.util.Pagination;
import com.ds.e_commerce_backend.util.dto.orders.CreateOrderRequest;
import com.ds.e_commerce_backend.util.dto.orders.OrderQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid ;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService ;


    // 取得 _ 所有 / 特定條件( 可選 : required = false 或 defaultValue = "..." ) 訂單
    @GetMapping( "/users/{userId}/orders" )
    public ResponseEntity< Pagination<Orders> > getOrders(

            // 帳號 / 使用者 id
            @PathVariable Integer userId ,

            // 分頁
            @RequestParam( defaultValue = "10" ) @Max( 1000 ) @Min( 0 ) Integer limit , // 每次取得資料數
            @RequestParam( defaultValue = "0" ) @Min( 0 ) Integer offset                // 每隔多少筆

    ){

        OrderQueryParams orderQueryParams = new OrderQueryParams() ;
        orderQueryParams.setUserId( userId ) ;
        orderQueryParams.setLimit( limit ) ;
        orderQueryParams.setOffset( offset ) ;

        // 取得 _ 訂單列表
        List< Orders > ordersList = ordersService.getOrders( orderQueryParams ) ;

        // 取得 _ 訂單總數
        Integer count = ordersService.countOrder( orderQueryParams ) ;

        // 分頁
        Pagination< Orders > page = new Pagination<>() ;
        page.setLimit( limit ) ;
        page.setOffset( offset ) ;
        page.setTotal( count ) ;
        page.setResults( ordersList ) ;

        return ResponseEntity.status( HttpStatus.OK ).body( page ) ;

    }



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
