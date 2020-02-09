package com.heady.ecommerceapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VariantsItem(

	@field:SerializedName("color")
	var color: String? = null,

	@field:SerializedName("size")
	var size: Int? = null,

	@field:SerializedName("price")
	var price: Int? = null,

	@field:SerializedName("id")
	var id: Int? = null
) : Parcelable