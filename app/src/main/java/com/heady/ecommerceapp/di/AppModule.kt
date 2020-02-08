package com.heady.ecommerceapp.di

import androidx.room.Room
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.data.DataRepository
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.local.Database
import com.heady.ecommerceapp.data.local.DatabaseManager
import com.heady.ecommerceapp.data.local.RoomDatabase
import com.heady.ecommerceapp.data.remote.Api
import com.heady.ecommerceapp.data.remote.ApiManager
import com.heady.ecommerceapp.viewmodel.RootDataViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<Api> { ApiManager() }

    single {
        Room.databaseBuilder(
            androidContext(),
            RoomDatabase::class.java,
            androidContext().getString(R.string.app_name)
        ).build()
    }

    single<Database> { DatabaseManager(get()) }

    single<DataSource> { DataRepository(get(), get()) }

}

val viewModelModule = module {
    viewModel { RootDataViewModel(get()) }
}