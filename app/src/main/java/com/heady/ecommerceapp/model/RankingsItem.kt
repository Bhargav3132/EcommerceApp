package com.heady.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class RankingsItem(

	@field:SerializedName("ranking")
	val ranking: String? = null,

	@field:SerializedName("products")
	val products: List<RankingProductItem?>? = null
)