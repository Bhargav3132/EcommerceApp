package com.heady.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heady.ecommerceapp.model.CategoriesItem

@Dao
interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryAPI(categories: List<CategoriesItem>)

    @Query("SELECT * from CategoriesItem")
    suspend fun getCategory(): List<CategoriesItem>

    @Query("SELECT * from CategoriesItem where id in (:categoryId) ")
    suspend fun getCategoryByChild(categoryId: List<Int>): List<CategoriesItem>

}