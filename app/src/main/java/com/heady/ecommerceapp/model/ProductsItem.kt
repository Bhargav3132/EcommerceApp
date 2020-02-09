package com.heady.ecommerceapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.heady.ecommerceapp.data.local.CustomTypeConverters
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ProductsItem(

    @field:SerializedName("date_added")
    var dateAdded: String? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @TypeConverters(CustomTypeConverters::class)
    @field:SerializedName("tax")
    var tax: Tax? = null,

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "categoryId")
    var categoryId: Int? = null,

    @TypeConverters(CustomTypeConverters::class)
    @field:SerializedName("variants")
    var variants: List<VariantsItem?>? = null
) : Parcelable