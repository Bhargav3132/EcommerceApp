package com.heady.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class RankingProductItem(

    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("view_count")
    val viewCount: Int? = null,
    @field:SerializedName("order_count")
    val orderCount: Int? = null,
    @field:SerializedName("shares")
    val shares: Int? = null


)