package com.example.listapplication.model.database.repository

import com.example.listapplication.model.database.Item

object ItemHolder {

    val items: List<Item> by lazy {
        Thread.sleep(3000)
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i))
        }
        itemsArrayList
    }

}


