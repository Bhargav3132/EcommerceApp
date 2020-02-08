package com.heady.ecommerceapp.model

import com.google.gson.annotations.SerializedName

data class Tax(

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("value")
	var value: Double? = null
)