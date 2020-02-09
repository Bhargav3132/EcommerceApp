package com.heady.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heady.ecommerceapp.model.ProductsItem

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(products: List<ProductsItem>)

    @Query("SELECT * from ProductsItem where categoryId in (:categoryId)")
    suspend fun getProductByCategory(categoryId: Int): List<ProductsItem>

    @Query("SELECT * from ProductsItem where id in (SELECT productId from RankingProductItem where rankingId in (:rankingId) order by viewCount, orderCount, shares DESC)")
    suspend fun getProductByRanking(rankingId: Int): List<ProductsItem>

}