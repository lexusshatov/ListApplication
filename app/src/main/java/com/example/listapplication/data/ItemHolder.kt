package com.example.listapplication.data

object ItemHolder {

    val items: List<Item> by lazy {
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i))
        }
        itemsArrayList
    }

}


