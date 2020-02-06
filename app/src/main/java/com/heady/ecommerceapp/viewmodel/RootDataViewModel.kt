package com.heady.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.remote.simaple.ApiResponse
import com.heady.ecommerceapp.model.RootDataModel
import kotlinx.coroutines.launch

class RootDataViewModel(private val dataRepository: DataSource) : ViewModel() {

    val apiGetRootData = MutableLiveData<ApiResponse<RootDataModel>>()

    fun getRootData() {
        apiGetRootData.value = ApiResponse.Loading()
        viewModelScope.launch {

            val result = dataRepository.getRootData()
            apiGetRootData.value = result

        }
    }

}