package com.example.listapplication.view.ui.list

import android.content.Context
import android.content.SharedPreferences
import com.example.listapplication.base.BaseViewModel
import com.example.listapplication.base.Interactor
import com.example.listapplication.model.data.Item

class ItemListViewModel(
    interactors: Set<Interactor<ItemListState, ItemListAction>>
) : BaseViewModel<ItemListState, ItemListAction>(
    interactors = interactors,
    reducer = ItemListReducer()
) {

    fun loadItems() {
        action(ItemListAction.LoadItems)
    }

    fun saveItem(item: Item) {
        action(ItemListAction.SaveItem(item))
    }

    fun test() {
        action(ItemListAction.Test("Test string"))
    }

}