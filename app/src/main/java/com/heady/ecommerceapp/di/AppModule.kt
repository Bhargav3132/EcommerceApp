package com.heady.ecommerceapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.data.DataRepository
import com.heady.ecommerceapp.data.DataSource
import com.heady.ecommerceapp.data.local.Database
import com.heady.ecommerceapp.data.local.DatabaseManager
import com.heady.ecommerceapp.data.local.RoomDatabase
import com.heady.ecommerceapp.data.pref.Preference
import com.heady.ecommerceapp.data.pref.PreferenceManager
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

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    single<Database> { DatabaseManager(get()) }

    single<DataSource> { DataRepository(get(), get()) }

    single<Preference> { PreferenceManager(get()) }

}

val viewModelModule = module {
    viewModel { RootDataViewModel(get()) }
}