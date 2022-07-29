package com.ds.e_commerce_backend.service.impl;

import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao ;


    // 取得 _ 特定 ( id ) 帳號
    @Override
    public Users getUserById(Integer userId) {

       return usersDao.getUserById( userId ) ;

    }

    // 新增 _ 帳號
    @Override
    public Integer register( Users users ) {

        return usersDao.createUser( users ) ;

    }


}
