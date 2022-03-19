package com.ds.e_commerce_backend.service.impl;
import com.ds.e_commerce_backend.dao.ProductsDao;
import com.ds.e_commerce_backend.model.Products;
import com.ds.e_commerce_backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao productsDao ;

    @Override
    public Products getProductById(Integer productId) {

        return productsDao.getProductById( productId ) ;

    }

}
