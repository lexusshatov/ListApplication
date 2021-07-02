package com.example.listapplication.model.database.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {
    val data = itemDao.findAll()

    suspend fun refresh(){
        withContext(Dispatchers.IO){
            val items = ItemHolder.items
            itemDao.add(items)
            Log.d("DATA", "data refreshed")
        }
    }
}