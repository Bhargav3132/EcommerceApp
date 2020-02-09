package com.heady.ecommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.model.VariantsItem
import kotlinx.android.synthetic.main.row_variant.view.*

class VariantAdapter(var variantList: ArrayList<VariantsItem> = arrayListOf(), var onVariantItemClick: OnVariantItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_variant, parent, false))
    }

    override fun getItemCount(): Int {
        return variantList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RankingViewHolder) {
            holder.onBind(position)
        }

    }

    fun addProductItem(variantItems: ArrayList<VariantsItem>) {
        variantList.addAll(variantItems)
        notifyDataSetChanged()
    }

    fun getAllItems(): ArrayList<VariantsItem> {
        return variantList
    }

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            itemView.tvPrice.text = variantList[position].price.toString()

            variantList[position].color?.let {
                itemView.groupColor.isVisible = true
                itemView.tvColor.text = it
            } ?: kotlin.run {
                itemView.groupColor.isVisible = false
            }

            variantList[position].size?.let {
                itemView.groupSize.isVisible = true
                itemView.tvSize.text = it.toString()
            } ?: kotlin.run {
                itemView.groupSize.isVisible = false
            }

            itemView.cvVariant.tag = position

            itemView.cvVariant.setOnClickListener {
                if (onVariantItemClick != null) {

                    selectedPos = it.tag as Int

                    onVariantItemClick.onVariantItemClick(it.tag as Int)

                    notifyDataSetChanged()
                }
            }

            itemView.cvVariant.isSelected = position == selectedPos

        }
    }

    interface OnVariantItemClick {
        fun onVariantItemClick(position: Int)
    }

}