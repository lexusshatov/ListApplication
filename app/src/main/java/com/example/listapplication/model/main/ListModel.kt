package com.example.listapplication.model.main

import com.example.listapplication.model.task.ItemListLoad
import com.example.listapplication.model.task.OnItemListLoadCallback

class ListModel: ListContractModel {

    override fun loadItemList(callback: OnItemListLoadCallback) {
        ItemListLoad(callback).execute()
    }

}