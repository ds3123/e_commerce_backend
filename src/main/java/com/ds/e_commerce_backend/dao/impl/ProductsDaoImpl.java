package com.ds.e_commerce_backend.dao.impl;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.dao.model.Products;
import com.ds.e_commerce_backend.dao.rowmapper.ProductsRowMapper;
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


    // 取得 _ 所有商品
    @Override
    public List<Products> getProducts() {

        String sql = "SELECT product_id, product_name, category, image_url, price, stock, " +
                     "description, created_date, last_modified_date FROM products" ;

        Map<String , Object> map = new HashMap<>() ;

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
