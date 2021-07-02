package com.example.listapplication.model.database.repository

import com.example.listapplication.model.database.Item

object ItemHolder {

    val items: List<Item> by lazy {
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i, "$i item", "description of $i item"))
        }
        itemsArrayList
    }

    fun getItemById(id: Int): Item? {
        items.forEach { if (it.id == id) return it }
        return null
    }
}

