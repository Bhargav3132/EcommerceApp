package com.heady.ecommerceapp.data

import com.heady.ecommerceapp.data.local.Database
import com.heady.ecommerceapp.data.remote.Api
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RootDataModel

class DataRepository(
    private val api: Api,
    private val database: Database
) : DataSource {
    override suspend fun addProduct(products: List<ProductsItem>) {
        database.addProduct(products)
    }

    override suspend fun addCategoryAPI(categories: List<CategoriesItem>) {
        database.addCategoryAPI(categories)
    }

    override suspend fun getRootData(): ApiResponse<RootDataModel> {
        return api.getRootData()
    }


}