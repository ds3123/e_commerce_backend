package com.ds.e_commerce_backend.controller;
import com.ds.e_commerce_backend.dto.ProductRequest;
import com.ds.e_commerce_backend.model.Products;
import com.ds.e_commerce_backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService ;


    // 測試
    @GetMapping( "/test" )
    public String test(){

       return "Hello Heroku" ;

    }




    // 取得 _ 商品
    @GetMapping( "/products/{productId}" )
    public ResponseEntity<Products> getProduct(@PathVariable Integer productId){

         Products product = productsService.getProductById( productId ) ;

         if( product != null ){
             return ResponseEntity.status( HttpStatus.OK ).body( product ); // 200
         }else{
             return ResponseEntity.status( HttpStatus.NOT_FOUND).build();   // 404
         }

    }

    // 新增 _ 商品
    @PostMapping("/products")
    public ResponseEntity<Products> createProduct( @RequestBody @Valid
                                                   ProductRequest productRequest ){
        // 新增商品後，回傳 _ 新增的商品 id
        Integer productId = productsService.createProduct( productRequest ) ;

        // 取得新增的商品
        Products createdProduct  = productsService.getProductById( productId ) ;

        // 回傳新增成功訊息( 201 / 新增的商品資訊 )
        return ResponseEntity.status( HttpStatus.CREATED ).body( createdProduct ) ;

    }

    // 修改 _ 商品
    @PutMapping("/products/{productId}")
    public ResponseEntity<Products> updateProduct( @PathVariable Integer productId ,
                                                   @RequestBody @Valid ProductRequest productRequest
                                                 ){

        Products product = productsService.getProductById( productId ) ;

        // 先確認欲更新的商品，是否存在 --> 若不存在，回傳 404
        if( product == null ){
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).build() ;
        }

        // 修改商品
        productsService.updateProduct( productId , productRequest ) ;

        // 取得更新後的商品
        Products updatedProduct = productsService.getProductById( productId ) ;

        // 回傳更新成功訊息( 200 / 更新的商品資訊 )
        return ResponseEntity.status( HttpStatus.OK ).body( updatedProduct ) ;

    }


    // 刪除 _ 商品
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct( @PathVariable Integer productId ){

        // 刪除商品
        /*
            * 不需檢查 _ 商品是否存在
            * 對於前端來說，僅需得知結果：商品是否存在( 回傳 204 NO_CONTENT )
              --> 只要確認商品消失不見
        */

         productsService.deleteProductById( productId ) ;

         // 回傳刪除成功訊息 ( 204 )
         return ResponseEntity.status( HttpStatus.NO_CONTENT ).build() ; // 204

    }


}
