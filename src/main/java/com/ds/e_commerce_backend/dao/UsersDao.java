package com.ds.e_commerce_backend.dao;


import com.ds.e_commerce_backend.dao.model.Users;

public interface UsersDao {

    // 取得 _ 特定 ( id ) 帳號
    Users getUserById( Integer userId ) ;

    // 新增 _ 帳號
    Integer createUser( Users users ) ;


}
