package com.heady.ecommerceapp.data.local

import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RankingProductItem
import com.heady.ecommerceapp.model.RankingsItem

interface Database {

    suspend fun addCategoryAPI(categories: List<CategoriesItem> )

    suspend fun addProduct(products: List<ProductsItem>)

    suspend fun addRanking(rankings: RankingsItem) : Long

    suspend fun addRankingProduct(rankingProductItem: List<RankingProductItem>)

}