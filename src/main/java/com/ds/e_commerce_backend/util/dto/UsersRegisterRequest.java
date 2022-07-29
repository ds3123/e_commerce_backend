package com.ds.e_commerce_backend.util.dto;

/*

   作用 :
       接住前端 ( for 請求 : 註冊 _ 帳號 ) 傳來的參數，並進行驗證 : @NotBlank , @Email

*/


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsersRegisterRequest {

    @NotBlank
    @Email
    private String email ;

    @NotBlank
    private String password ;


    // Getter & Setter

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
}
