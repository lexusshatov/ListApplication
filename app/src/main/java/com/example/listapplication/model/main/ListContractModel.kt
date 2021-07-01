package com.example.listapplication.model.main

import com.example.listapplication.model.data.Item

interface ListContractModel {
    fun getItemList() : List<Item>?
}