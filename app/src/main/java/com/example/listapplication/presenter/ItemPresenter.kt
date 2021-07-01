package com.example.listapplication.presenter

import android.content.Intent
import android.util.Log
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.view.ItemContractView
import com.example.listapplication.view.ListActivity

class ItemPresenter {
    private var view: ItemContractView? = null

    fun attachView(view: ItemContractView){
        this.view = view
    }

    fun detachView(){
        view = null
    }

    fun viewIsReady(itemId: Int?){
        Log.d("ItemActivity", "item from intent: $itemId")
        if (itemId != null){
            val item = ItemHolder.getItemById(itemId)
            binding.itemId.text = item.id.toString()
            binding.itemName.text = item.name
            binding.itemDescription.text = item.description
        } else {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}