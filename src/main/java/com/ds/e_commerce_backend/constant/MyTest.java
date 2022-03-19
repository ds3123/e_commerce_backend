package com.ds.e_commerce_backend.constant;


// 測試 Enum 型別
public class MyTest {

    public static void main(String[] args) {

        // .name()
        ProductsCategory category = ProductsCategory.FOOD ;
        String s = category.name() ;

        System.out.println( s );   // FOOD

        // .valueOf()
        ProductsCategory category2 = ProductsCategory.valueOf( "BOOK" ) ;

        System.out.println( category2 );  // BOOK


    }

}
