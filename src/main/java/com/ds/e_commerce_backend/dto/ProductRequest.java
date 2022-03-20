
package com.ds.e_commerce_backend.dto;
import com.ds.e_commerce_backend.constant.ProductsCategory;
import javax.validation.constraints.NotNull;

/*

   @ 分擔處理 ( 避免 Products 類別過於龐雜 ) _ 前端傳來的 JSON 資料 ( Products 商品 )
     --> 驗證前端參數
       ＠NotNull

*/

public class ProductRequest {

    // 前端傳遞的參數
    @NotNull
    private String productName ;

    private ProductsCategory category ;  //  Enum 型別（ 可 Cmd+點選，快速檢視所列舉的類型 ）

    @NotNull
    private String imageUrl ;

    @NotNull
    private Integer price ;

    @NotNull
    private Integer stock ;

    private String description ;


    // Getter & Setter
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



}
