package com.natife.example.mviexample.data.interactors.list

import com.natife.example.mviexample.base.Interactor
import com.natife.example.mviexample.ui.list.ItemListAction
import com.natife.example.mviexample.ui.list.ItemListState
import com.natife.example.mviexample.util.ItemHolder

class GetItemsInteractor : Interactor<ItemListState, ItemListAction> {

    override suspend fun invoke(state: ItemListState, action: ItemListAction): ItemListAction {
        return if (action is ItemListAction.LoadItems) {
            ItemListAction.ItemsLoaded(ItemHolder.items)
        } else {
            ItemListAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.LoadItems
    }

}