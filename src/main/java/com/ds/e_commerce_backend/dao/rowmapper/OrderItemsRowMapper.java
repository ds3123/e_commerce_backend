package com.ds.e_commerce_backend.dao.rowmapper;

import com.ds.e_commerce_backend.dao.model.OrderItems;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderItemsRowMapper implements RowMapper< OrderItems > {

    @Override
    public OrderItems mapRow( ResultSet resultSet, int i ) throws SQLException {

        OrderItems orderItem = new OrderItems() ;

        // from 訂單項目資料表 [ order_items ]
        orderItem.setOrderItemId( resultSet.getInt( "order_item_id" ) ) ;
        orderItem.setOrderId( resultSet.getInt( "order_id" ) ) ;
        orderItem.setProductId( resultSet.getInt( "product_id" ) ) ;
        orderItem.setQuantity( resultSet.getInt( "quantity" ) ) ;
        orderItem.setAmount( resultSet.getInt( "amount" ) );

        // from 商品資料表 [ products ]
        orderItem.setProductName( resultSet.getString( "product_name" ) ) ;
        orderItem.setImageUrl( resultSet.getString( "image_url" ) ) ;

        return orderItem ;

    }

}
