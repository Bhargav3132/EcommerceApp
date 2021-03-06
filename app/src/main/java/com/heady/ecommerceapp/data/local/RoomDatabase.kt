package com.heady.ecommerceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RankingProductItem
import com.heady.ecommerceapp.model.RankingsItem

@Database(entities = [CategoriesItem::class, ProductsItem::class, RankingsItem::class,RankingProductItem::class], version = 1)
@TypeConverters(CustomTypeConverters::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getCategoryDAO(): CategoryDAO

    abstract fun getProductDAO(): ProductDAO

    abstract fun getRankingDAO(): RankingDAO

}