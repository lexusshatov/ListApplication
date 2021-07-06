package com.natife.example.mviexample.data.interactors.list

import com.natife.example.mviexample.base.Interactor
import com.natife.example.mviexample.ui.list.ItemListAction
import com.natife.example.mviexample.ui.list.ItemListState

class TestInteractor : Interactor<ItemListState, ItemListAction> {

    override suspend fun invoke(state: ItemListState, action: ItemListAction): ItemListAction {
        return if (action is ItemListAction.Test) {
            println("action data: ${action.testData}, current items size: ${state.items.size}")
            ItemListAction.None
        } else {
            ItemListAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.Test
    }
}