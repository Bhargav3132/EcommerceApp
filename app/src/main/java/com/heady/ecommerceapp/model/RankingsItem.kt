package com.heady.ecommerceapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class RankingsItem(

	@PrimaryKey
	var id : Int? = null,

	@field:SerializedName("ranking")
	var ranking: String? = null,

	@Ignore
	@field:SerializedName("products")
	var products: List<RankingProductItem?>? = null
) : Parcelable