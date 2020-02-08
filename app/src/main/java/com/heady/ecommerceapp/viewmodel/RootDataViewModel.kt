package com.heady.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RootDataModel
import kotlinx.coroutines.launch

class RootDataViewModel(private val dataRepository: DataSource) : ViewModel() {

    val apiGetRootData = MutableLiveData<ApiResponse<RootDataModel>>()

    fun getRootData() {
        apiGetRootData.value = ApiResponse.Loading()
        viewModelScope.launch {

            val result = dataRepository.getRootData()
            when (result) {
                is ApiResponse.Success -> {
                    result.data?.categories.let {

                        dataRepository.addCategoryAPI(it as List<CategoriesItem>)

                        // set category id to products
                        result.data?.categories?.forEach { categoriesItem ->
                            val categoryId = categoriesItem?.id
                            categoriesItem?.products?.forEach { productsItem ->
                                productsItem?.categoryId = categoryId
                            }

                            dataRepository.addProduct(categoriesItem?.products as List<ProductsItem>)

                        }

                    }
                }
            }

            apiGetRootData.value = result

        }
    }

}