package com.ds.e_commerce_backend.controller;

import com.ds.e_commerce_backend.dao.UsersDao;
import com.ds.e_commerce_backend.dao.model.Users;
import com.ds.e_commerce_backend.util.dto.users.UsersLoginRequest;
import com.ds.e_commerce_backend.util.dto.users.UsersRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private UsersDao usersDao;

    private ObjectMapper objectMapper = new ObjectMapper();


    private void register( UsersRegisterRequest userRegisterRequest) throws Exception {

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }


    // ----------------------------------

    // 註冊新帳號
    @Test
    public void register_success() throws Exception {

        UsersRegisterRequest userRegisterRequest = new UsersRegisterRequest();
        userRegisterRequest.setEmail("test1@gmail.com");
        userRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));

        // 檢查資料庫中的密碼不為明碼
        Users user = usersDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(userRegisterRequest.getPassword(), user.getPassword());
    }

    @Test
    public void register_invalidEmailFormat() throws Exception {

        UsersRegisterRequest userRegisterRequest = new UsersRegisterRequest();
        userRegisterRequest.setEmail("3gd8e7q34l9");
        userRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void register_emailAlreadyExist() throws Exception {

        // 先註冊一個帳號
        UsersRegisterRequest userRegisterRequest = new UsersRegisterRequest();
        userRegisterRequest.setEmail("test2@gmail.com");
        userRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

        // 再次使用同個 email 註冊
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 登入
    @Test
    public void login_success() throws Exception {
        // 先註冊新帳號
        UsersRegisterRequest userRegisterRequest = new UsersRegisterRequest();
        userRegisterRequest.setEmail("test3@gmail.com");
        userRegisterRequest.setPassword("123");

        register( userRegisterRequest );

        // 再測試登入功能
        UsersLoginRequest userLoginRequest = new UsersLoginRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword(userRegisterRequest.getPassword());

        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(userRegisterRequest.getEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    public void login_wrongPassword() throws Exception {

        // 先註冊新帳號
        UsersRegisterRequest userRegisterRequest = new UsersRegisterRequest();
        userRegisterRequest.setEmail("test4@gmail.com");
        userRegisterRequest.setPassword("123");

        register(userRegisterRequest);

        // 測試密碼輸入錯誤的情況
        UsersLoginRequest userLoginRequest = new UsersLoginRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword("unknown");

        String json = objectMapper.writeValueAsString( userLoginRequest );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_invalidEmailFormat() throws Exception {

        UsersLoginRequest userLoginRequest = new UsersLoginRequest();
        userLoginRequest.setEmail("hkbudsr324");
        userLoginRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_emailNotExist() throws Exception {

        UsersLoginRequest userLoginRequest = new UsersLoginRequest();
        userLoginRequest.setEmail("unknown@gmail.com");
        userLoginRequest.setPassword("123");

        String json = objectMapper.writeValueAsString( userLoginRequest );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }



}