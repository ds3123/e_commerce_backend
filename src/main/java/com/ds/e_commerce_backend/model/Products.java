package com.ds.e_commerce_backend.model;
import com.ds.e_commerce_backend.constant.ProductsCategory;

import java.util.Date;

// 對應 products 資料表
public class Products {

    // 資料表對應欄位
    private Integer productId ;
    private String productName ;
    private ProductsCategory category ;  //  Enum 型別（ 可 Cmd+點選，快速檢視所列舉的類型 ）
    private String imageUrl ;
    private Integer price ;
    private Integer stock ;
    private String description ;
    private Date createdDate ;
    private Date lastModifiedDate ;


    // Getter & Setter

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductsCategory getCategory() {
        return category;
    }

    public void setCategory(ProductsCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


}
