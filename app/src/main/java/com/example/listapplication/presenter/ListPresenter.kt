package com.example.listapplication.presenter

import com.example.listapplication.model.data.Item
import com.example.listapplication.model.main.ListModel
import com.example.listapplication.model.task.OnItemListLoadCallback
import com.example.listapplication.view.ListContractView

class ListPresenter(var view: ListContractView?) {
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

    private fun loadList(){
        listModel.loadItemList(object : OnItemListLoadCallback{
            override fun onLoadComplete(result: List<Item>) {
                view?.showList(result)
            }
        })
    }

    fun onItemListSelected(item: Item){
        view?.saveItem(item)
        view?.startItemActivity(item)
    }
}