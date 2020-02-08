package com.heady.ecommerceapp.data.local

import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem

interface Database {

    suspend fun addCategoryAPI(categories: List<CategoriesItem> )

    suspend fun addProduct(products: List<ProductsItem>)

}