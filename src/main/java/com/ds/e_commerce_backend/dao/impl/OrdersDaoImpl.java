package com.ds.e_commerce_backend.dao.impl;

import com.ds.e_commerce_backend.dao.OrdersDao;
import com.ds.e_commerce_backend.dao.model.OrderItems;
import com.ds.e_commerce_backend.dao.model.Orders;
import com.ds.e_commerce_backend.dao.rowmapper.OrderItemsRowMapper;
import com.ds.e_commerce_backend.dao.rowmapper.OrdersRowMapper;
import com.ds.e_commerce_backend.util.dto.orders.OrderQueryParams;
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


     // 共用方法
     private String addFilteringSql( String sql , Map< String , Object > map , OrderQueryParams orderQueryParams ){

         if( orderQueryParams.getUserId() != null ){

             sql = sql + " AND user_id = :userId" ;
             map.put( "userId" , orderQueryParams.getUserId() ) ;

         }

         return sql ;

     }


     // 取得 _ 特定 ( id ) 訂單
     @Override
     public Orders getOrderById( Integer orderId ) {

        String sql = "SELECT order_id , user_id , total_amount , created_date , last_modified_date " +
                     "FROM `orders` WHERE order_id = :orderId" ;

        Map< String , Object > map = new HashMap<>() ;
        map.put( "orderId" , orderId ) ;

        List< Orders > ordersList = namedParameterJdbcTemplate.query( sql , map , new OrdersRowMapper() ) ;

        if( ordersList.size() > 0 ){
           return ordersList.get( 0 ) ;
        }else{
          return null ;
        }

     }


     // 取得 _ 特定 id 訂單，所包含商品項目
     @Override
     public List< OrderItems > getOrderItemsByOrderId( Integer orderId ) {

        String sql = "SELECT oi.order_item_id , oi.order_id , oi.product_id , oi.quantity , oi.amount , p.product_name , p.image_url " +
                     "FROM order_items AS oi LEFT JOIN products AS p " +
                     "ON oi.product_id = p.product_id " +
                     "WHERE oi.order_id = :orderId" ;

        Map< String , Object > map = new HashMap<>() ;
        map.put( "orderId" , orderId  ) ;

        List< OrderItems > orderItemsList = namedParameterJdbcTemplate.query( sql , map , new OrderItemsRowMapper() ) ;

        return orderItemsList ;

     }


    // 取得 _ 訂單列表
    @Override
    public List<Orders> getOrders( OrderQueryParams orderQueryParams ) {

         String sql = "SELECT order_id , user_id , total_amount , created_date , last_modified_date FROM `orders` WHERE 1=1" ;

         Map< String , Object > map = new HashMap<>() ;

         // 串接 _ 查詢條件
         sql = addFilteringSql( sql , map , orderQueryParams ) ;

         // 排序
         sql = sql + " ORDER BY created_date DESC" ;  // 固定為 _ 建立時間 : 降冪排序 ( 不希望前端可以改變訂單順序 )

         // 分頁
         sql = sql + " LIMIT :limit OFFSET :offset" ;
         map.put( "limit" , orderQueryParams.getLimit() ) ;
         map.put( "offset" , orderQueryParams.getOffset() ) ;

         List< Orders > ordersList = namedParameterJdbcTemplate.query( sql , map , new OrdersRowMapper() ) ;

         return ordersList ;


    }

    // 取得 _ 訂單商品項目總數
    @Override
    public Integer countOrder( OrderQueryParams orderQueryParams ) {

         String sql = "SELECT count( * ) FROM `orders` WHERE 1=1" ;

         Map< String , Object > map = new HashMap<>() ;

         // 串接 _ 查詢條件
         sql = addFilteringSql( sql , map , orderQueryParams ) ;

         Integer total = namedParameterJdbcTemplate.queryForObject( sql , map , Integer.class ) ;

         return total ;

    }

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
