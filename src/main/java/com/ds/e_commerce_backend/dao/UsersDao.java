package com.ds.e_commerce_backend.dao;


import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.util.dto.UsersRegisterRequest;

public interface UsersDao {

    // 取得 _ 特定 ( id ) 帳號
    Users getUserById( Integer userId ) ;


    // 取得 _ 特定 ( email ) 帳號
    Users getUserByEmail( String email ) ;


    // 新增 _ 帳號
    Integer createUser( UsersRegisterRequest usersRegisterRequest ) ;


}
