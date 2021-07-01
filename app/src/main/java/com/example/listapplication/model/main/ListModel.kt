package com.example.listapplication.model.main

import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.ItemHolder

class ListModel: ListContractModel {
    override fun getItemList(): List<Item>? {
        return ItemHolder.items
    }
}