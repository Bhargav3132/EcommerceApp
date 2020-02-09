package com.heady.ecommerceapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.RankingsItem
import com.heady.ecommerceapp.ui.adapter.CategoryAdapter
import com.heady.ecommerceapp.ui.adapter.RankingAdapter
import com.heady.ecommerceapp.ui.base.BaseFragment
import com.heady.ecommerceapp.utils.Constant
import com.heady.ecommerceapp.viewmodel.RootDataViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment private constructor() : BaseFragment(), CategoryAdapter.OnCategoryItemClick, RankingAdapter.OnRankingItemClick {

    companion object {
        fun getInstance() = CategoryFragment()
    }

    private val rootDataViewModel: RootDataViewModel by viewModel()

    private lateinit var rankingAdapter: RankingAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_category
    }

    override fun initializeComponent(view: View) {

        initRankingRecyclerView()
        initCategoryRecyclerView()

        initObserver()

        rootDataViewModel.getRootData()

    }

    private fun initRankingRecyclerView() {

        rvRankings.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)

        rankingAdapter = RankingAdapter(onRankingItemClick = this)
        rvRankings.adapter = rankingAdapter

    }

    private fun initCategoryRecyclerView() {

        rvCategory.layoutManager = LinearLayoutManager(activity!!)

        categoryAdapter = CategoryAdapter(onCategoryItemClick = this)
        rvCategory.adapter = categoryAdapter

    }

    private fun initObserver() {
        rootDataViewModel.apiGetRootData.observe(this, Observer {
            when (it) {
                is ApiResponse.Loading -> {
                    groupAll.isVisible = false
                    setProgressBarVisible(true)
                    setEmptyView(false)
                }
                is ApiResponse.ApiError -> {
                    groupAll.isVisible = false
                    setProgressBarVisible(false)
                    setEmptyView(true)
                }
                is ApiResponse.ServerError -> {
                    groupAll.isVisible = false
                    setProgressBarVisible(false)
                    setEmptyView(true)
                }
                is ApiResponse.NoInternetConnection -> {
                    groupAll.isVisible = false
                    setProgressBarVisible(false)
                    setEmptyView(true)
                }
                is ApiResponse.Success -> {
                    groupAll.isVisible = true
                    setProgressBarVisible(false)
                    setEmptyView(false)

                    rankingAdapter?.addRankingItem(it.data as ArrayList<RankingsItem>)

                    rootDataViewModel.getCategory()
                }
            }

        })

        // category observer
        rootDataViewModel.apiGetCategory.observe(this, Observer {
            when (it) {
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    categoryAdapter?.addRankingItem(it.data as ArrayList<CategoriesItem>)
                }
            }

        })

    }

    override fun onRankingItemClick(position: Int) {

        rankingAdapter.let {

            val productFragment = ProductFragment.getInstance()
            val bundle = Bundle()
            bundle.putParcelable(Constant.BUNDLE_RANKING, it.getAllItems()[position])
            productFragment.arguments = bundle

            addFragment(R.id.flContainer, this, productFragment)

        }

    }

    override fun onCategoryItemClick(position: Int) {

        categoryAdapter.let {
            if (it.getAllItems()[position].childCategories != null && it.getAllItems()[position].childCategories?.size!! > 0) {

                val subCategoryFragment = SubCategoryFragment.getInstance()
                val bundle = Bundle()
                bundle.putParcelable(Constant.BUNDLE_CATEGORY, it.getAllItems()[position])
                subCategoryFragment.arguments = bundle

                addFragment(R.id.flContainer, this, subCategoryFragment)
            } else {

                val productFragment = ProductFragment.getInstance()
                val bundle = Bundle()
                bundle.putParcelable(Constant.BUNDLE_CATEGORY, it.getAllItems()[position])
                productFragment.arguments = bundle

                addFragment(R.id.flContainer, this, productFragment)

            }
        }


    }

}