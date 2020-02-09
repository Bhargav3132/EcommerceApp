package com.heady.ecommerceapp.data.local

import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RankingProductItem
import com.heady.ecommerceapp.model.RankingsItem

class DatabaseManager(roomDatabase: RoomDatabase) : Database {

    private val categoryDAO = roomDatabase.getCategoryDAO()
    private val productDAO = roomDatabase.getProductDAO()
    private val rankingDAO = roomDatabase.getRankingDAO()

    override suspend fun getProductByRanking(rankingId: Int): List<ProductsItem> {
        return productDAO.getProductByRanking(rankingId)
    }

    override suspend fun getProductByCategory(categoryId: Int): List<ProductsItem> {
        return productDAO.getProductByCategory(categoryId)
    }

    override suspend fun getCategoryByChild(categoryId: List<Int>): List<CategoriesItem> {
        return categoryDAO.getCategoryByChild(categoryId)
    }

    override suspend fun getCategory(): List<CategoriesItem> {
        return categoryDAO.getCategory()
    }

    override suspend fun getRanking(): List<RankingsItem> {
        return rankingDAO.getRanking()
    }

    override suspend fun addRankingProduct(rankingProductItem: List<RankingProductItem>) {
        rankingDAO.addRankingProduct(rankingProductItem)
    }

    override suspend fun addProduct(products: List<ProductsItem>) {
        productDAO.addProduct(products)
    }

    override suspend fun addCategoryAPI(categories: List<CategoriesItem>) {
        categoryDAO.addCategoryAPI(categories)
    }

    override suspend fun addRanking(rankings: RankingsItem): Long {
        return rankingDAO.addRanking(rankings)
    }

}