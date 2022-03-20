package com.ds.e_commerce_backend.service;

import com.ds.e_commerce_backend.dto.ProductRequest;
import com.ds.e_commerce_backend.model.Products;

public interface ProductsService {

    // 取得 _ 商品
    Products getProductById( Integer productId ) ;

    // 新增 _ 商品
    Integer createProduct( ProductRequest productRequest ) ;

}
