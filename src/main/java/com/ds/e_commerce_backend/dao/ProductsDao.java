package com.ds.e_commerce_backend.dao;
import com.ds.e_commerce_backend.dao.model.Products;
import java.util.List;


public interface ProductsDao {

    // 取得 _ 所有商品
    List<Products> getProducts();

    // 取得 _ 特定 ( id ) 商品
    Products getProductById( Integer productId );

    // 新增 _ 商品
    Integer createProduct( Products products ) ;

    // 更新 _ 商品
    void updateProduct( Integer productId , Products products ) ;

    // 刪除 _ 商品
    void deleteProductById( Integer productId ) ;


}
