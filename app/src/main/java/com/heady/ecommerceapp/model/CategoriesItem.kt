package com.heady.ecommerceapp.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.heady.ecommerceapp.data.local.CustomTypeConverters
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class CategoriesItem(

    @field:SerializedName("name")
    var name: String? = null,

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null,

    @TypeConverters(CustomTypeConverters::class)
    @ColumnInfo(name = "childCategory")
    @field:SerializedName("child_categories")
    var childCategories: List<Int?>? = null,

    @Ignore
    @field:SerializedName("products")
    var products: List<ProductsItem?>? = null
) : Parcelable