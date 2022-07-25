package com.ds.e_commerce_backend.service.impl;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.service.ProductsService;
import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao productsDao ;


    // 取得 _ 所有 / 特定條件( 可選 ) 商品
    @Override
    public List<Products> getProducts( ProductsCategory category , String search ) {

        return productsDao.getProducts( category , search ) ;

    }

    // 取得 _ 特定 ( id ) 商品
    @Override
    public Products getProductById(Integer productId) {

        return productsDao.getProductById( productId ) ;

    }

    // 新增 _ 商品
    @Override
    public Integer createProduct(Products products) {

        return productsDao.createProduct( products ) ;

    }

    // 更新 _ 商品
    @Override
    public void updateProduct(Integer productId, Products products) {

        productsDao.updateProduct( productId , products ) ;

    }


    // 刪除 _ 商品
    @Override
    public void deleteProductById(Integer productId) {

        productsDao.deleteProductById( productId ) ;

    }

}
