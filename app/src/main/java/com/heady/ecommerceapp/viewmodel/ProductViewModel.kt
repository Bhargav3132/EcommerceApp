package com.heady.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.ProductsItem
import kotlinx.coroutines.launch

class ProductViewModel(private val dataRepository: DataSource) : ViewModel() {

    val dbProductData = MutableLiveData<ApiResponse<List<ProductsItem>>>()

    fun getProductByCategory(categoryId: Int) {
        dbProductData.value = ApiResponse.Loading()
        viewModelScope.launch {
            dbProductData.value = ApiResponse.Success(dataRepository.getProductByCategory(categoryId))
        }
    }

    fun getProductByRanking(rankingId: Int) {
        dbProductData.value = ApiResponse.Loading()
        viewModelScope.launch {
            dbProductData.value = ApiResponse.Success(dataRepository.getProductByRanking(rankingId))
        }
    }

}