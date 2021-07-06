package com.natife.example.mviexample.ui.list

import com.natife.example.mviexample.base.BaseViewModel
import com.natife.example.mviexample.base.Interactor

class ItemListViewModel(
    interactors: Set<Interactor<ItemListState, ItemListAction>>
) : BaseViewModel<ItemListState, ItemListAction>(
    interactors = interactors,
    reducer = ItemListReducer()
) {

    fun loadItems() {
        action(ItemListAction.LoadItems)
    }

    fun test() {
        action(ItemListAction.Test("Test string"))
    }

}