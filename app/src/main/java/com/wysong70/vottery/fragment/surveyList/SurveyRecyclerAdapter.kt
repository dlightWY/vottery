package com.wysong70.vottery.fragment.surveyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wysong70.vottery.fragment.dataClass.Item
import com.wysong70.vottery.databinding.SurveyListItemRecyclerBinding

class SurveyRecyclerAdapter(private var items: List<Item>)
    : RecyclerView.Adapter<SurveyRecyclerAdapter.SurveyRecyclerHolder>() {
    var listData = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyRecyclerHolder {
        val binding = SurveyListItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SurveyRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: SurveyRecyclerHolder, position: Int) {
        holder.setSurveyItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItem(itemList: List<Item>) {
        items = itemList
        notifyDataSetChanged()
    }

    class SurveyRecyclerHolder(val binding: SurveyListItemRecyclerBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun setSurveyItem(surveyItem: Item) {
            binding.item = surveyItem
        }

    }
}

