package com.ds.e_commerce_backend.controller;
import com.ds.e_commerce_backend.model.Products;
import com.ds.e_commerce_backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService ;

    @GetMapping( "/products/{productId}" )
    public ResponseEntity<Products> getProduct(@PathVariable Integer productId){

         Products product = productsService.getProductById( productId ) ;

         if( product != null ){
             return ResponseEntity.status( HttpStatus.OK ).body( product ); // 200
         }else{
             return ResponseEntity.status( HttpStatus.NOT_FOUND).build();   // 404
         }

    }


}
