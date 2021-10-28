package com.wysong70.vottery.fragment.surveyList

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wysong70.vottery.fragment.dataClass.Item

object AdapterBindings {
    const val TEST = "test@123"

    @BindingAdapter("bind:item")
    @JvmStatic
    fun bindItem(recyclerView: RecyclerView, itemList: List<Item>?) {
        Log.d(TEST, "bind:item")
        with(recyclerView.adapter as SurveyRecyclerAdapter) {
            itemList?.let {
                setItem(it)
            }
        }
    }
}