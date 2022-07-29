package com.ds.e_commerce_backend.dao.impl;


import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.dao.rowmapper.UsersRowMapper;
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
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;


    // 取得 _ 特定 ( id ) 帳號
    @Override
    public Users getUserById( Integer userId ) {

        String sql = "SELECT user_id , email , password , created_date , last_modified_date " +
                     "FROM users WHERE user_id = :userId" ;

        Map< String , Object > map = new HashMap<>() ;
        map.put( "userId" , userId ) ;

        List<Users> usersList = namedParameterJdbcTemplate.query( sql , map , new UsersRowMapper() ) ;

        if( usersList.size() > 0  ){
            return usersList.get( 0 ) ;
        }else{
            return null ;
        }

    }

    // 新增 _ 帳號
    @Override
    public Integer createUser( Users users ) {

        String sql = "INSERT INTO users( email , password , created_date , last_modified_date ) " +
                     "VALUES ( :email , :password , :created_date , :last_modified_date )" ;

        // 前端傳來的值與時間( Date )，放入 Map map
        Map< String , Object > map = new HashMap<>() ;
        map.put( "email" , users.getEmail() ) ;
        map.put( "password" , users.getPassword() ) ;
        Date now = new Date() ;
        map.put( "created_date" , now ) ;
        map.put( "last_modified_date" , now ) ;

        KeyHolder keyHolder = new GeneratedKeyHolder() ;

        // 新增 _ 帳號
        namedParameterJdbcTemplate.update( sql , new MapSqlParameterSource( map ) , keyHolder ) ;

        // 取得 _ 新增帳號 id
        int userId = keyHolder.getKey().intValue() ;

        return userId ;

    }


}
