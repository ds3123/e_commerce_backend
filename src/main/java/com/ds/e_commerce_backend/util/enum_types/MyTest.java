package com.ds.e_commerce_backend.util.enum_types;


// 測試 Enum 型別
public class MyTest {

    public static void main(String[] args) {

        // .name()
        com.ds.e_commerce_backend.util.enum_types.ProductsCategory category = com.ds.e_commerce_backend.util.enum_types.ProductsCategory.FOOD ;
        String s = category.name() ;

        System.out.println( s );   // FOOD

        // .valueOf()
        com.ds.e_commerce_backend.util.enum_types.ProductsCategory category2 = ProductsCategory.valueOf( "BOOK" ) ;

        System.out.println( category2 );  // BOOK


    }

}
