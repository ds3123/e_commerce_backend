package com.ds.e_commerce_backend.dao.rowmapper;
import com.ds.e_commerce_backend.dao.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {


    @Override
    public Users mapRow( ResultSet resultSet , int i ) throws SQLException {

        Users users = new Users() ;

        users.setUserId( resultSet.getInt( "user_id" ) ) ;
        users.setEmail( resultSet.getString( "email" ) ) ;
        users.setPassword( resultSet.getString( "password" ) ) ;
        users.setCreatedDate( resultSet.getTimestamp( "created_date" ) ) ;
        users.setLastModifiedDate( resultSet.getTimestamp( "last_modified_date" ) ) ;

        return users ;

    }


}
