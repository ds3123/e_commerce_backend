package com.ds.e_commerce_backend.dao.model;

/*

    @ 作用 :
     1. 對應 `users` 資料表
     2. 處理前端傳來的 JSON 資料 ( Users 使用者帳號 ) --> 驗證前端參數 : ＠NotBlank

 */


import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Users {

   private Integer userId ;

   @NotBlank
   private String email ;

   @NotBlank
   private String password ;

   private Date createdDate ;
   private Date lastModifiedDate ;


    // Getter & Setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}