package com.heady.ecommerceapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tax(

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("value")
	var value: Double? = null
) : Parcelable