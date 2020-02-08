package com.heady.ecommerceapp.viewmodel

import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.pref.Preference
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RankingProductItem
import com.heady.ecommerceapp.model.RootDataModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RootDataViewModel(private val dataRepository: DataSource) : ViewModel(), ComponentCallbacks {

    val apiGetRootData = MutableLiveData<ApiResponse<RootDataModel>>()

    val preference: Preference by inject()

    fun getRootData() {
        apiGetRootData.value = ApiResponse.Loading()
        viewModelScope.launch {

            if (!preference.isDataDownloaded()) {

                val result = dataRepository.getRootData()
                when (result) {
                    is ApiResponse.Success -> {

                        // add category and product
                        addCategoryAndProduct(result)

                        // add ranking and products
                        addRankingAndProduct(result)
                    }
                }

                preference.setDataDownloaded(true)

                apiGetRootData.value = result
            } else {
                // loading from db
                apiGetRootData.value = ApiResponse.Success(null)
            }

        }
    }

    private suspend fun addRankingAndProduct(result: ApiResponse.Success<RootDataModel>) {
        result.data?.rankings.let { rankingList ->

            rankingList?.forEach { rankingItem ->
                val id = rankingItem?.let { dataRepository.addRanking(it) }

                rankingItem?.products?.forEach { rankingProductItem ->

                    rankingProductItem?.rankingId = id.toString()

                }

                dataRepository.addRankingProduct(rankingItem?.products as List<RankingProductItem>)

            }

        }
    }

    private suspend fun addCategoryAndProduct(result: ApiResponse.Success<RootDataModel>) {
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

    override fun onConfigurationChanged(newConfig: Configuration) {

    }

    override fun onLowMemory() {

    }

}