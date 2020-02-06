package com.heady.ecommerceapp.di

import com.heady.ecommerceapp.viewmodel.RootDataViewModel
import com.heady.ecommerceapp.data.DataRepository
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.remote.Api
import com.heady.ecommerceapp.data.remote.ApiManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<Api> { ApiManager() }

    single<DataSource> { DataRepository(get()) }

}

val viewModelModule = module {
    viewModel { RootDataViewModel(get()) }
}