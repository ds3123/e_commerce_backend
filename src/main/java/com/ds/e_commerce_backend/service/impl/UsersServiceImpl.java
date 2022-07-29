package com.ds.e_commerce_backend.service.impl;

import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.service.UsersService;
import com.ds.e_commerce_backend.util.dto.UsersRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao ;

    // Log 紀錄
    private final static Logger log = LoggerFactory.getLogger( UsersServiceImpl.class ) ;


    // 取得 _ 特定 ( id ) 帳號
    @Override
    public Users getUserById( Integer userId ) {

       return usersDao.getUserById( userId ) ;

    }

    // 新增 _ 帳號
    @Override
    public Integer register( UsersRegisterRequest usersRegisterRequest ) {

        // * 檢查 _ 前端傳過來的 email，是否已經註冊過
        Users user = usersDao.getUserByEmail( usersRegisterRequest.getEmail() ) ;

        if( user != null ){  // 該 email 已有使用者註冊

            log.warn( "該 Email : {} ， 已經註冊過" , usersRegisterRequest.getEmail() ) ;  // 回傳 log 訊息
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ; // 擲出例外，停止執行，回傳 400 狀態碼

        }

        return usersDao.createUser( usersRegisterRequest ) ;

    }

}


