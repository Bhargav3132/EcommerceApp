package com.heady.ecommerceapp.data.remote

import com.heady.ecommerceapp.model.RootDataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("json")
    suspend fun getRootData(): Response<RootDataModel>

}