package com.ds.e_commerce_backend.util.dto.orders;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/*

   @ 接住前端 ( for 請求：新增訂單 ) 傳來的參數，並進行驗證 : @NotEmpty

*/


public class CreateOrderRequest {

    @NotEmpty
    private List< BuyItemRequest > buyItemList ;  //  購買項目清單


    // Getter & Setter
    public List<BuyItemRequest> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItemRequest> buyItemList) {
        this.buyItemList = buyItemList;
    }


}
