package com.heady.ecommerceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RankingProductItem(

    @PrimaryKey
    var rpId: Int?,

    @ColumnInfo(name = "rankingId")
    var rankingId: String,

    @ColumnInfo(name = "productId")
    @field:SerializedName("id")
    var productId: Int? = null,
    @field:SerializedName("view_count")
    var viewCount: Int? = null,
    @field:SerializedName("order_count")
    var orderCount: Int? = null,
    @field:SerializedName("shares")
    var shares: Int? = null


)