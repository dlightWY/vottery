package com.wysong70.vottery.fragment.surveyList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wysong70.vottery.fragment.dataClass.Item

class SurveyListViewModel: ViewModel(){

    private val _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>>
        get() = _itemList

    fun onStart() {
        showItemList()
    }

    private fun showItemList() {
        _itemList.value = arrayListOf(
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item(),
            Item()
        )
    }
}