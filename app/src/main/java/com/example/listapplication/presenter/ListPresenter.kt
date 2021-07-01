package com.example.listapplication.presenter

import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.model.main.ListModel
import com.example.listapplication.view.ListContractView
import kotlinx.coroutines.*

class ListPresenter {
    private var view: ListContractView? = null
    private val listModel = ListModel()

    fun attachView(view: ListContractView){
        this.view = view
    }

    fun detachView(){
        view = null
    }

    fun viewIsReady(){
        view?.startBackground()
        loadList()
    }

    private fun loadList() = GlobalScope.launch {
        //imitate query to database
        delay(3000)
        val items = ItemHolder.items
        runBlocking (Dispatchers.Main){
            view?.showList(items)
        }
    }

    fun onItemListSelected(item: Item){
        view?.saveItem(item)
        view?.startItemActivity(item)
    }
}