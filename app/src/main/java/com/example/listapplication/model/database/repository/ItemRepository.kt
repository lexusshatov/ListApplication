package com.example.listapplication.model.database.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.listapplication.R
import com.example.listapplication.model.database.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao, private val context: Context) {
    val data = itemDao.findAll()
    val item: Item?
        get() = getItemData()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val items = ItemHolder.items
            itemDao.add(items)
            Log.d("DATA", "data refreshed, item id: ${item!!.id}")
        }
    }

    fun getItemById(id: Int): Item? {
        return ItemHolder.getItemById(id)
    }

    private fun getItemData(): Item? {
        val itemId = context
            .getSharedPreferences(context.getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .getInt(context.getString(R.string.APP_PREFERENCES_ITEM_ID), -1)
        return ItemHolder.getItemById(itemId)
    }
}