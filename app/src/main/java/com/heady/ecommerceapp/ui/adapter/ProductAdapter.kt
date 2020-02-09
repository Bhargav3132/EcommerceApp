package com.heady.ecommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.model.ProductsItem
import kotlinx.android.synthetic.main.row_product.view.*

class ProductAdapter(var productList: ArrayList<ProductsItem> = arrayListOf(), var onProductItemClick: OnProductItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RankingViewHolder) {
            holder.onBind(position)
        }

    }

    fun addProductItem(productItems: ArrayList<ProductsItem>) {
        productList.addAll(productItems)
        notifyDataSetChanged()
    }

    fun getAllItems(): ArrayList<ProductsItem> {
        return productList
    }

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            itemView.tvProductName.text = productList[position].name
            itemView.tvProductPrice.text = itemView.context.getString(R.string.price, productList[position].variants?.get(0)?.price.toString())
            itemView.cvProduct.tag = position

            itemView.cvProduct.setOnClickListener {
                if (onProductItemClick != null) {
                    onProductItemClick.onProductItemClick(it.tag as Int)
                }
            }
        }
    }

    interface OnProductItemClick {
        fun onProductItemClick(position: Int)
    }

}