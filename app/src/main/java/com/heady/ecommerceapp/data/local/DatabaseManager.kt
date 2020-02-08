package com.heady.ecommerceapp.data.local

import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem

class DatabaseManager(roomDatabase: RoomDatabase) : Database {

    private val categoryDAO = roomDatabase.getCategoryDAO()
    private val productDAO = roomDatabase.getProductDAO()

    override suspend fun addProduct(products: List<ProductsItem>) {
        productDAO.addProduct(products)
    }

    override suspend fun addCategoryAPI(categories: List<CategoriesItem>) {
        categoryDAO.addCategoryAPI(categories)
    }

}