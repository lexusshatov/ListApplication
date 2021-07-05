package com.example.listapplication.model.data

import androidx.lifecycle.MutableLiveData

object ItemHolder {

    val items: MutableLiveData<List<Item>> by lazy {
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i))
        }
        val itemsData = MutableLiveData<List<Item>>()
        itemsData.postValue(itemsArrayList)
        itemsData
    }

    fun getItemById(id: Int): Item? {
        items.value?.forEach { if (it.id == id) return it }
        return null
    }

}


