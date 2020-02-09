package com.heady.ecommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.model.RankingsItem
import kotlinx.android.synthetic.main.row_ranking.view.*

class RankingAdapter(var rankingList: ArrayList<RankingsItem> = arrayListOf(), var onRankingItemClick: OnRankingItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_ranking, parent, false))
    }

    override fun getItemCount(): Int {
        return rankingList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RankingViewHolder) {
            holder.onBind(position)
        }

    }

    fun addRankingItem(rankingItems: ArrayList<RankingsItem>) {
        rankingList.addAll(rankingItems)
        notifyDataSetChanged()
    }

    fun getAllItems(): ArrayList<RankingsItem> {
        return rankingList
    }

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            itemView.tvRanking.text = rankingList[position]?.ranking
            itemView.tvRanking.tag = position

            itemView.tvRanking.setOnClickListener {
                if (onRankingItemClick != null) {
                    onRankingItemClick.onRankingItemClick(it.tag as Int)
                }
            }

        }
    }

    interface OnRankingItemClick {
        fun onRankingItemClick(position: Int)
    }

}