package com.heady.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class RootDataModel(

	@field:SerializedName("rankings")
	val rankings: List<RankingsItem?>? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null
)