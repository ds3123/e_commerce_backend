package com.ds.e_commerce_backend.service;


import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.util.dto.users.UsersLoginRequest;
import com.ds.e_commerce_backend.util.dto.users.UsersRegisterRequest;

public interface UsersService {

   // 取得 _ 特定 ( id ) 帳號
   Users getUserById( Integer userId ) ;

   // 新增 _ 帳號
   Integer register( UsersRegisterRequest usersRegisterRequest ) ;

   // 登入 _ 帳號
   Users login(UsersLoginRequest usersLoginRequest ) ;


}
