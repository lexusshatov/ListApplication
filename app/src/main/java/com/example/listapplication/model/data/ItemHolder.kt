package com.example.listapplication.model.data

object ItemHolder {

    val items: List<Item> by lazy {
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i))
        }
        itemsArrayList
    }

    fun getItemById(id: Int): Item? {
        items.forEach { if (it.id == id) return it }
        return null
    }

}


