package com.ds.e_commerce_backend.dao.rowmapper;

import com.ds.e_commerce_backend.dao.model.Orders;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrdersRowMapper implements RowMapper< Orders > {

    @Override
    public Orders mapRow( ResultSet resultSet, int i ) throws SQLException {

        Orders order = new Orders() ;

        order.setOrderId( resultSet.getInt( "order_id" ) ) ;
        order.setUserId( resultSet.getInt( "user_id" ) ) ;
        order.setTotalAmount( resultSet.getInt( "total_amount" ) ) ;
        order.setCreatedDate( resultSet.getTimestamp( "created_date" ) ) ;
        order.setLastModifiedDate( resultSet.getTimestamp( "last_modified_date" ) ) ;

        return order ;

    }

}
