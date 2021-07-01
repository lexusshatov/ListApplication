package com.example.listapplication.model.task

import android.os.AsyncTask
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.ItemHolder

class ItemListLoad(private val callback: OnItemListLoadCallback?): AsyncTask<Void, Void, List<Item>>() {
    override fun doInBackground(vararg params: Void?): List<Item> {
        Thread.sleep(3000)
        return ItemHolder.items
    }

    override fun onPostExecute(result: List<Item>) {
        super.onPostExecute(result)
        callback?.onLoadComplete(result)
    }
}