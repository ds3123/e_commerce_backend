package com.ds.e_commerce_backend.controller;

import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UsersController {

    @Autowired
    private UsersService usersService ;


    // 新增 _ 帳號
    @PostMapping("/users/register")
    public ResponseEntity<Users> register( @RequestBody @Valid Users users ){

       // 新增帳號後，回傳 _ 新增的帳號 id
       Integer userId = usersService.register( users ) ;

       // 取得新增的帳號
       Users registerUser = usersService.getUserById( userId ) ;

       // 回傳新增成功訊息( 201 / 新增的帳號資訊 )
       return ResponseEntity.status( HttpStatus.CREATED ).body( registerUser ) ;


    }




}
