package com.heady.ecommerceapp.data

import com.heady.ecommerceapp.data.remote.Api
import com.heady.ecommerceapp.model.RootDataModel
import com.heady.ecommerceapp.data.remote.simaple.ApiResponse

class DataRepository(
    private val api: Api
) : DataSource {
    override suspend fun getRootData(): ApiResponse<RootDataModel> {
        return api.getRootData()
    }


}