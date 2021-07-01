package com.example.listapplication.model.data

object ItemHolder {

    val items: List<Item> by lazy {
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i, "$i item", "description of $i item"))
        }
        itemsArrayList
    }

    fun getItemById(id: Int): Item {
        return items.first { it.id == id }
    }
}


