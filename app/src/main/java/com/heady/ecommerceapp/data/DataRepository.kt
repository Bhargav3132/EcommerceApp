package com.heady.ecommerceapp.data

import com.heady.ecommerceapp.data.local.Database
import com.heady.ecommerceapp.data.remote.Api
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.*

class DataRepository(
    private val api: Api,
    private val database: Database
) : DataSource {
    override suspend fun addRanking(rankings: RankingsItem): Long {
        return database.addRanking(rankings)
    }

    override suspend fun addRankingProduct(rankingProductItem: List<RankingProductItem>) {
        database.addRankingProduct(rankingProductItem)
    }


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