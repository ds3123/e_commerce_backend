package com.ds.e_commerce_backend.dao.impl;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.model.Products;
import com.ds.e_commerce_backend.rowmapper.ProductsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductsDaoImpl implements ProductsDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;


    @Override
    public Products getProductById(Integer productId) {

        String sql = "SELECT product_id, product_name, category, image_url, price, stock, " +
                     "description, created_date, last_modified_date FROM products " +
                     "WHERE product_id = :productId" ;

        Map<String , Object> map = new HashMap<>() ;
        map.put( "productId" , productId ) ;

        List<Products> productsList = namedParameterJdbcTemplate.query( sql, map, new ProductsRowMapper() );

        // 填寫判斷，避免 List 為空
        if( productsList.size() > 0 ){
             return productsList.get( 0 ) ;
        }else{
            return null ;
        }


    }


}
