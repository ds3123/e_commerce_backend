package com.ds.e_commerce_backend.dao.impl;

import com.ds.e_commerce_backend.dao.OrdersDao;
import com.ds.e_commerce_backend.dao.model.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrdersDaoImpl implements OrdersDao {

     @Autowired
     private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;


     // 建立 _ 該筆訂單
     @Override
     public Integer createOrder( Integer userId, Integer totalAmount ) {

          String sql = "INSERT INTO `orders`( user_id , total_amount , created_date , last_modified_date ) "+
                       "VALUES( :userId , :totalAmount , :createdDate , :lastModifiedDate )" ;

          Map< String , Object > map = new HashMap<>() ;
          map.put( "userId" , userId ) ;
          map.put( "totalAmount" , totalAmount ) ;

          Date now = new Date() ;
          map.put( "createdDate" , now ) ;
          map.put( "lastModifiedDate" , now ) ;

          KeyHolder keyHolder = new GeneratedKeyHolder() ;

          // 新增訂單
          namedParameterJdbcTemplate.update( sql , new MapSqlParameterSource( map ) , keyHolder ) ;

          // 訂單 id
          int orderId = keyHolder.getKey().intValue() ;

          return orderId ;


     }

     // 新增 _ 訂單項目
     @Override
     public void createOrderItems( Integer orderId , List<OrderItems> orderItemsList ){

        // 1. for 迴圈 : 多次執行 sql 新增語法 --> 簡單,但沒有效率
//          for( OrderItems orderItems : orderItemsList ){
//              String sql = "INSERT INTO order_item( order_id , product_id , quantity , amount ) " +
//                           "VALUES( :orderId , :productId , :quantity , :amount )" ;
//              Map< String , Object > map = new HashMap<>() ;
//              map.put( "orderId" , orderId ) ;
//              map.put( "productId" , orderItems.getProductId() ) ;
//              map.put( "quantity" , orderItems.getQuantity() ) ;
//              map.put( "amount" , orderItems.getAmount() ) ;
//              namedParameterJdbcTemplate.update( sql , map ) ;
//          }

        // 2. bathUpdate() : 一次性執行 sql 新增語法
          String sql = "INSERT INTO order_items( order_id , product_id , quantity , amount ) " +
                       "VALUES( :orderId , :productId , :quantity , :amount )" ;

          MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[ orderItemsList.size() ] ;

          for( int i = 0 ; i < orderItemsList.size() ; i++ ){

              OrderItems orderItem = orderItemsList.get( i ) ;

              parameterSources[ i ] = new MapSqlParameterSource() ;
              parameterSources[ i ].addValue( "orderId" , orderId ) ;
              parameterSources[ i ].addValue( "productId" , orderItem.getProductId() ) ;
              parameterSources[ i ].addValue( "quantity" , orderItem.getQuantity() ) ;
              parameterSources[ i ].addValue( "amount" , orderItem.getAmount() ) ;

          }

          namedParameterJdbcTemplate.batchUpdate( sql , parameterSources ) ;

     }








}
