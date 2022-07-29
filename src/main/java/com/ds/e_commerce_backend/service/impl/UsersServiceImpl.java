package com.ds.e_commerce_backend.service.impl;

import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.service.UsersService;
import com.ds.e_commerce_backend.util.dto.UsersLoginRequest;
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
        Users registerUser = usersDao.getUserByEmail( usersRegisterRequest.getEmail() ) ;

        if( registerUser != null ){  // 該 email 已有使用者註冊

            log.warn( "該 Email : {} ， 已經註冊過" , usersRegisterRequest.getEmail() ) ;  // 回傳 log 訊息
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ; // 擲出例外，停止執行，回傳 400 狀態碼

        }

        return usersDao.createUser( usersRegisterRequest ) ;

    }

    // 登入 _ 帳號
    @Override
    public Users login( UsersLoginRequest usersLoginRequest ) {

        Users loginUser = usersDao.getUserByEmail( usersLoginRequest.getEmail() ) ;

        // # 驗證 _ 該 email

            // 尚未有使用者註冊
            if( loginUser == null ){

                log.warn( "該 Email : {} ， 尚未註冊" , usersLoginRequest.getEmail() ) ;  // 回傳 log 訊息
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ;           // 擲出例外，停止執行，回傳 400 狀態碼

            }

            // 密碼是否正確
            if( loginUser.getPassword().equals( usersLoginRequest.getPassword() ) ){

                 return loginUser ;

            }else{

                log.warn( "該 Email : {} ， 密碼不正確" , usersLoginRequest.getEmail()  );
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST ) ;            // 擲出例外，停止執行，回傳 400 狀態碼

            }


    }











}


