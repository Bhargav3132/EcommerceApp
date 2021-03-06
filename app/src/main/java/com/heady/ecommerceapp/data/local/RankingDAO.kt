package com.heady.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heady.ecommerceapp.model.RankingProductItem
import com.heady.ecommerceapp.model.RankingsItem

@Dao
interface RankingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRanking(rankings: RankingsItem) : Long

    @Insert
    suspend fun addRankingProduct(rankingProductItem: List<RankingProductItem>)

    @Query("SELECT * from RankingsItem")
    suspend fun getRanking() : List<RankingsItem>

}