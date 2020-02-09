package com.heady.ecommerceapp.ui.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.model.ProductsItem
import com.heady.ecommerceapp.model.VariantsItem
import com.heady.ecommerceapp.ui.adapter.VariantAdapter
import com.heady.ecommerceapp.ui.base.BaseFragment
import com.heady.ecommerceapp.utils.Constant
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment private constructor() : BaseFragment(), VariantAdapter.OnVariantItemClick {

    companion object {
        fun getInstance() = ProductDetailFragment()
    }

    private lateinit var productAdapter: VariantAdapter

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_product_detail
    }

    override fun initializeComponent(view: View) {


        arguments?.let { bundle ->
            when {
                bundle.containsKey(Constant.BUNDLE_PRODUCT) -> {

                    val productItem = bundle.getParcelable<ProductsItem>(Constant.BUNDLE_PRODUCT)


                    productItem?.let { product ->

                        tvTitle.text = product.name
                        tvProductName.text = product.name
                        tvTaxLabel.text = product.tax?.name
                        tvTaxValue.text = product.tax?.value.toString()

                        tvPrice.text = product.variants?.get(0)?.price.toString()

                        initCategoryRecyclerView(product.variants)

                    }

                }
                else -> return
            }
        }

        ibBack.setOnClickListener {
            activity!!.onBackPressed()
        }

    }

    private fun initCategoryRecyclerView(variants: List<VariantsItem?>?) {

        val gridLayoutManager = GridLayoutManager(activity!!, 2)
        rvVariants.layoutManager = gridLayoutManager

        productAdapter = VariantAdapter(variantList = variants as ArrayList<VariantsItem>, onVariantItemClick = this)
        rvVariants.adapter = productAdapter

    }

    override fun onVariantItemClick(position: Int) {

        productAdapter.let {

            tvPrice.text = it.getAllItems()[position]?.price.toString()


        }

    }

}