package com.ds.e_commerce_backend.dao.rowmapper;
import com.ds.e_commerce_backend.util.enum_types.ProductsCategory;
import com.ds.e_commerce_backend.dao.model.Products;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
    配合 namedParameterJdbcTemplate.query() 第 3 個參數 :
    將所查詢的 "資料庫欄位數據"，轉換成 "Java 類別物件" ( for Products 類別 )
 */

public class ProductsRowMapper implements RowMapper< Products > {

    @Override
    public Products mapRow( ResultSet resultSet , int i ) throws SQLException {

         /*

          根據 category 由原先的 String 類型 ，轉為 ProductsCategory( Enum ) 類型，以符合目前 Products 類別中，
          category 目前的資料類型 ( ProductsCategory)

          簡要寫法 ( 寫成一行 ) :
          products.setCategory( ProductsCategory.valueOf( resultSet.getString("category") )  );

         */

        // 查詢出 category 欄位字串
        String categoryStr        = resultSet.getString( "category" ) ;
        // 將 category 欄位字串，轉為 ProductsCategory( Enum ) 類型
        ProductsCategory category = ProductsCategory.valueOf( categoryStr ) ;



        Products products = new Products() ;

        // 利用 setter ，設定 _ 所查詢 products 資料表各個欄位值
        products.setProductId( resultSet.getInt( "product_id" ) );
        products.setProductName( resultSet.getString( "product_name" ) );
        products.setCategory( category );
        products.setImageUrl( resultSet.getString("image_url") );
        products.setPrice( resultSet.getInt("price") );
        products.setStock( resultSet.getInt("stock") );
        products.setDescription( resultSet.getString("description") );
        products.setCreatedDate( resultSet.getTimestamp( "created_date" ) );
        products.setLastModifiedDate( resultSet.getTimestamp("last_modified_date") );

        return products ;

    }

}
