package com.heady.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class Tax(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("value")
	val value: Double? = null
)