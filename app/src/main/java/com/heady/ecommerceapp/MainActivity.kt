package com.heady.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.heady.ecommerceapp.data.remote.simaple.ApiResponse
import com.heady.ecommerceapp.viewmodel.RootDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val rootDataViewModel: RootDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObserver()

        rootDataViewModel.getRootData()

    }

    private fun initObserver() {
        rootDataViewModel.apiGetRootData.observe(this, Observer {
            when(it){
                is ApiResponse.Loading -> {
                    Log.e("Data Loading","@@@@@@@@@@@@@@@@@@@@@@")
                }
                is ApiResponse.ApiError -> {
                    Log.e("Data error","<<<<<----->>>>>")
                }
                is ApiResponse.ServerError -> {
                    Log.e("Data server error","<<<<<----->>>>>")
                }
                is ApiResponse.Success -> {
                    Log.e("Data download done","<<<<<----->>>>>")
                }
            }

        })
    }
}
