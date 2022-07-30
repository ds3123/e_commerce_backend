package com.ds.e_commerce_backend.dao;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.util.dto.products.ProductQueryParams;

import java.util.List;


public interface ProductsDao {

    // 計算 _ 資料總筆數
    Integer countProducts( ProductQueryParams productQueryParams ) ;

    // 取得 _ 所有 / 特定條件( 可選 ) 商品
    List<Products> getProducts( ProductQueryParams productQueryParams );

    // 取得 _ 特定 ( id ) 商品
    Products getProductById( Integer productId );

    // 新增 _ 商品
    Integer createProduct( Products products ) ;

    // 更新 _ 商品
    void updateProduct( Integer productId , Products products ) ;

    // 扣除 _ 商品庫存
    void updateStock( Integer productId , Integer productStock ) ;

    // 刪除 _ 商品
    void deleteProductById( Integer productId ) ;


}
