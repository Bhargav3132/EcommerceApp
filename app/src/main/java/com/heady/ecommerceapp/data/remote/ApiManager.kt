package com.heady.ecommerceapp.data.remote

import com.heady.ecommerceapp.BuildConfig
import com.heady.ecommerceapp.model.RootDataModel
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.data.remote.helper.executeApiHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager : Api {
    private val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return@lazy Retrofit.Builder()
            .baseUrl("https://stark-spire-93433.herokuapp.com/")
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getRootData(): ApiResponse<RootDataModel> {
        return executeApiHelper { apiService.getRootData() }
    }

}