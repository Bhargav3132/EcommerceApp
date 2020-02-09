package com.heady.ecommerceapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.data.remote.helper.ApiResponse
import com.heady.ecommerceapp.model.CategoriesItem
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.RankingsItem
import com.heady.ecommerceapp.ui.adapter.ProductAdapter
import com.heady.ecommerceapp.ui.base.BaseFragment
import com.heady.ecommerceapp.utils.Constant
import com.heady.ecommerceapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment private constructor() : BaseFragment(), ProductAdapter.OnProductItemClick {

    companion object {
        fun getInstance() = ProductFragment()
    }

    private val productViewModel: ProductViewModel by viewModel()

    private lateinit var productAdapter: ProductAdapter

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_product
    }

    override fun initializeComponent(view: View) {


        arguments?.let { bundle ->
            when {
                bundle.containsKey(Constant.BUNDLE_CATEGORY) -> {

                    initCategoryRecyclerView()

                    initObserver()

                    val categoriesItem = bundle.getParcelable<CategoriesItem>(Constant.BUNDLE_CATEGORY)

                    tvTitle.text = categoriesItem?.name
                    categoriesItem?.id?.let { productViewModel.getProductByCategory(it) }
                }
                bundle.containsKey(Constant.BUNDLE_RANKING) -> {

                    initCategoryRecyclerView()

                    initObserver()

                    val rankingItem = bundle.getParcelable<RankingsItem>(Constant.BUNDLE_RANKING)

                    tvTitle.text = rankingItem?.ranking

                    rankingItem?.id?.let { productViewModel.getProductByRanking(it) }

                }
                else -> return
            }
        }
        ibBack.setOnClickListener {
            activity!!.onBackPressed()
        }

    }

    private fun initCategoryRecyclerView() {

        val gridLayoutManager = GridLayoutManager(activity!!, 2)
        rvProducts.layoutManager = gridLayoutManager

        productAdapter = ProductAdapter(onProductItemClick = this)
        rvProducts.adapter = productAdapter

    }

    private fun initObserver() {

        // category observer
        productViewModel.dbProductData.observe(this, Observer {
            when (it) {
                is ApiResponse.Loading -> {
                    setProgressBarVisible(true)
                    setEmptyView(false)
                }
                is ApiResponse.Success -> {
                    setProgressBarVisible(false)
                    setEmptyView(false)
                    productAdapter?.addProductItem(it.data as ArrayList<ProductsItem>)
                }
            }

        })

    }

    override fun onProductItemClick(position: Int) {

        productAdapter?.let {

            val productDetailFragment = ProductDetailFragment.getInstance()
            val bundle = Bundle()
            bundle.putParcelable(Constant.BUNDLE_PRODUCT, productAdapter.getAllItems()[position])
            productDetailFragment.arguments = bundle

            addFragment(R.id.flContainer, this, productDetailFragment)
        }

    }


}