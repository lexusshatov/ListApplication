package com.natife.example.mviexample.ui.list

import android.content.Context
import com.natife.example.mviexample.base.BaseViewModel
import com.natife.example.mviexample.base.Interactor
import com.natife.example.mviexample.data.model.Item

class ItemListViewModel(
    interactors: Set<Interactor<ItemListState, ItemListAction>>
) : BaseViewModel<ItemListState, ItemListAction>(
    interactors = interactors,
    reducer = ItemListReducer()
) {

    fun loadItems() {
        action(ItemListAction.LoadItems)
    }

    fun saveItem(context: Context, item: Item) {
        action(ItemListAction.SaveItem(context, item))
    }

    fun test() {
        action(ItemListAction.Test("Test string"))
    }

}