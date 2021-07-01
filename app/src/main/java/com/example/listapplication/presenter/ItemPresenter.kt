package com.example.listapplication.presenter

import android.util.Log
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.view.ItemContractView

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
            view?.showItem(ItemHolder.getItemById(itemId))
        } else {
            view?.backToStartActivity()
        }
    }
}