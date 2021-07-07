package com.example.listapplication.model.data.interactors.list

import com.example.listapplication.base.Interactor
import com.example.listapplication.view.ui.list.ItemListAction
import com.example.listapplication.view.ui.list.ItemListState
import com.example.listapplication.view.util.ItemHolder

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