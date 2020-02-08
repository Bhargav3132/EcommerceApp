package com.heady.ecommerceapp.data.remote

import com.heady.ecommerceapp.model.RootDataModel
import com.heady.ecommerceapp.data.remote.helper.ApiResponse


interface Api {

    suspend fun getRootData(): ApiResponse<RootDataModel>

}
