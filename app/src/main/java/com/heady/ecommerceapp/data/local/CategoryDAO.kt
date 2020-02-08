package com.heady.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.heady.ecommerceapp.model.CategoriesItem

@Dao
interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryAPI(categories: List<CategoriesItem>)

}