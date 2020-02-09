package com.heady.ecommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.model.CategoriesItem
import kotlinx.android.synthetic.main.row_category.view.*

class CategoryAdapter(var categoryList: ArrayList<CategoriesItem> = arrayListOf(), var onCategoryItemClick: OnCategoryItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RankingViewHolder) {
            holder.onBind(position)
        }

    }

    fun addRankingItem(categoryItems: ArrayList<CategoriesItem>) {
        categoryList.addAll(categoryItems)
        notifyDataSetChanged()
    }

    fun getAllItems(): ArrayList<CategoriesItem> {
        return categoryList
    }

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            itemView.tvCategory.text = categoryList[position]?.name
            itemView.tvCategory.tag = position

            itemView.tvCategory.setOnClickListener {
                if (onCategoryItemClick != null) {
                    onCategoryItemClick.onCategoryItemClick(it.tag as Int)
                }
            }
        }
    }

    interface OnCategoryItemClick {
        fun onCategoryItemClick(position: Int)
    }

}