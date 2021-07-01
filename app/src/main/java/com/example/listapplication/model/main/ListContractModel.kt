package com.example.listapplication.model.main

import com.example.listapplication.model.task.OnItemListLoadCallback

interface ListContractModel {
    fun loadItemList(callback: OnItemListLoadCallback)
}