package com.example.listapplication.model.data

object ItemHolder {

    val items: List<Item> by lazy {
        //loading data...
        Thread.sleep(1500)
        val itemsArrayList = ArrayList<Item>(20)
        for (i in 0..19){
            itemsArrayList.add(Item(i))
        }
        itemsArrayList
    }

    fun getItemById(id: Int): Item? {
        //search item...
        Thread.sleep(500)
        items.forEach { if (it.id == id) return it }
        return null
    }

}


