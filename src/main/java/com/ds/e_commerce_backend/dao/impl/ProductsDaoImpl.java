package com.ds.e_commerce_backend.dao.impl;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.dao.rowmapper.ProductsRowMapper;
import com.ds.e_commerce_backend.util.dto.ProductQueryParams;
import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductsDaoImpl implements ProductsDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    // 取得 _ 所有 / 特定條件( 可選 ) 商品
    @Override
    public List<Products> getProducts( ProductQueryParams productQueryParams ) {


                      // WHERE 1=1 ( 若 category 為 null， WHERE 1=1 ( 永遠為 true ) 等同沒有設任何查詢條件 )
                      // 此設定是為了以下 _ 拼接查詢條件用
                      // 僅在 Spring JDBC Template 需要， Spring Data JPA 會自動處理動態多條件查詢
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, " +
                     "description, created_date, last_modified_date FROM products WHERE 1 = 1" ;

        Map<String , Object> map = new HashMap<>() ;

        // * 若有查詢條件．可拼接在 WHERE 1=1 之後（ AND 前有空白 ）

        // category ( 商品類別 )
        if( productQueryParams.getCategory() != null ){
            sql = sql + " AND category = :category" ;
            map.put( "category" , productQueryParams.getCategory().name() ) ;  // category 為 Enum 類型，須使用 name()，轉換為字串
        }

        // search ( 關鍵字 )
        if( productQueryParams.getSearch() != null ){
            sql = sql + " AND product_name LIKE :search" ;
            map.put( "search" , "%" + productQueryParams.getSearch() + "%" ) ;
        }


        List<Products> productsList = namedParameterJdbcTemplate.query( sql , map , new ProductsRowMapper() ) ;

        return productsList ;


    }

    // 取得 _ 特定 ( id ) 商品
    @Override
    public Products getProductById( Integer productId ) {

        String sql = "SELECT product_id, product_name, category, image_url, price, stock, " +
                     "description, created_date, last_modified_date FROM products " +
                     "WHERE product_id = :productId" ;

        Map<String , Object> map = new HashMap<>() ;
        map.put( "productId" , productId ) ;

        List<Products> productsList = namedParameterJdbcTemplate.query( sql , map , new ProductsRowMapper() ) ;

        // 填寫判斷，避免 List 為空
        if( productsList.size() > 0 ){
            return productsList.get( 0 ) ;
        }else{
            return null ;
        }


    }


    // 新增 _ 商品
    @Override
    public Integer createProduct( Products products) {

        String sql = "INSERT INTO products( product_name , category , image_url , " +
                     "price , stock , description , created_date , last_modified_date ) " +
                     "VALUES ( :productName , :category , :imageUrl , :price , :stock , :description ," +
                     " :createdDate , :lastModifiedDate )" ;

        Map< String , Object > map = new HashMap<>() ;
        map.put( "productName" , products.getProductName() ) ;
        map.put( "category" , products.getCategory().toString() ) ; // 須 toString() 轉為字串
        map.put( "imageUrl" , products.getImageUrl() ) ;
        map.put( "price" , products.getPrice() ) ;
        map.put( "stock" , products.getStock() ) ;
        map.put( "description" , products.getDescription() ) ;

        Date now = new Date() ;
        map.put( "createdDate" , now ) ;
        map.put( "lastModifiedDate" , now ) ;

        KeyHolder keyHolder = new GeneratedKeyHolder() ;

        // 新增商品
        namedParameterJdbcTemplate.update( sql , new MapSqlParameterSource( map ) , keyHolder ) ;

        // 取得新增商品 id
        int productId = keyHolder.getKey().intValue() ;

        return productId ;


    }


    // 更新 _ 商品
    @Override
    public void updateProduct( Integer productId , Products products ) {

        String sql = "UPDATE products SET product_name = :productName , category = :category , " +
                     "image_url = :imageUrl , price = :price , stock = :stock , " +
                     "description = :description , last_modified_date = :lastModifiedDate " +
                     "WHERE product_id = :productId" ;

        Map<String , Object> map = new HashMap<>() ;

        map.put( "productId" , productId ) ;

        map.put( "productName" , products.getProductName() ) ;
        map.put( "category" , products.getCategory().toString() ) ;
        map.put( "imageUrl" , products.getImageUrl() ) ;
        map.put( "price" , products.getPrice() ) ;
        map.put( "stock" , products.getStock() ) ;
        map.put( "description" , products.getDescription() ) ;

        map.put( "lastModifiedDate" , new Date() ) ;  // 最後修改時間

        namedParameterJdbcTemplate.update( sql , map ) ;


    }


    // 刪除 _ 商品
    @Override
    public void deleteProductById( Integer productId ) {

        String sql = "DELETE FROM products WHERE product_id = :productId" ;

        Map<String , Object> map = new HashMap<>() ;
        map.put( "productId" , productId ) ;

        namedParameterJdbcTemplate.update( sql , map );

    }


}
