package com.ds.e_commerce_backend.util.dto.orders;

/*

   @ 接住前端 ( for 請求：單一購買項目 ) 傳來的參數，並進行驗證 : @NotNull

*/


import javax.validation.constraints.NotNull ;

public class BuyItemRequest {

    @NotNull
    private Integer productId ;  // 購買商品 id

    @NotNull
    private Integer quantity ;   // 購買商品 數量


    // Getter & Setter
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
