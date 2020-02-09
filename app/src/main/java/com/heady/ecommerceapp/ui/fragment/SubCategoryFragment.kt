package com.heady.ecommerceapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.ui.adapter.CategoryAdapter
import com.heady.ecommerceapp.ui.base.BaseFragment
import com.heady.ecommerceapp.utils.Constant
import com.heady.ecommerceapp.viewmodel.RootDataViewModel
import kotlinx.android.synthetic.main.fragment_category.rvCategory
import kotlinx.android.synthetic.main.fragment_sub_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubCategoryFragment private constructor() : BaseFragment(), CategoryAdapter.OnCategoryItemClick {

    companion object {
        fun getInstance() = SubCategoryFragment()
    }

    private val rootDataViewModel: RootDataViewModel by viewModel()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_sub_category
    }

    override fun initializeComponent(view: View) {


        if (arguments != null && arguments?.containsKey(Constant.BUNDLE_CATEGORY)!!) {

            initCategoryRecyclerView()

            initObserver()

            val categoriesItem = arguments?.getParcelable<CategoriesItem>(Constant.BUNDLE_CATEGORY)

            tvTitle.text = categoriesItem?.name
            rootDataViewModel.getCategoryByChild(categoriesItem?.childCategories as List<Int>)
        }

        ibBack.setOnClickListener {
            activity!!.onBackPressed()
        }

    }

    private fun initCategoryRecyclerView() {

        rvCategory.layoutManager = LinearLayoutManager(activity!!)

        categoryAdapter = CategoryAdapter(onCategoryItemClick = this)
        rvCategory.adapter = categoryAdapter

    }

    private fun initObserver() {

        // category observer
        rootDataViewModel.apiGetCategory.observe(this, Observer {
            when (it) {
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    setProgressBarVisible(false)
                    setEmptyView(false)
                    categoryAdapter?.addRankingItem(it.data as ArrayList<CategoriesItem>)
                }
            }

        })

    }

    override fun onCategoryItemClick(position: Int) {

        categoryAdapter?.let {
            if (it.getAllItems()[position].childCategories != null && it.getAllItems()[position].childCategories?.size!! > 0) {

                val subCategoryFragment = SubCategoryFragment.getInstance()
                val bundle = Bundle()
                bundle.putParcelable(Constant.BUNDLE_CATEGORY, categoryAdapter.getAllItems()[position])
                subCategoryFragment.arguments = bundle

                addFragment(R.id.flContainer, this, subCategoryFragment)
            } else {

                val productFragment = ProductFragment.getInstance()
                val bundle = Bundle()
                bundle.putParcelable(Constant.BUNDLE_CATEGORY, categoryAdapter.getAllItems()[position])
                productFragment.arguments = bundle

                addFragment(R.id.flContainer, this, productFragment)

            }
        }


    }

}