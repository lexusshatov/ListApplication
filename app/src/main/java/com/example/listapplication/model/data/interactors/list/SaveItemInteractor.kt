package com.example.listapplication.model.data.interactors.list

import android.content.Context
import com.example.listapplication.base.Interactor
import com.example.listapplication.view.ui.list.ItemListAction
import com.example.listapplication.view.ui.list.ItemListState
import com.example.listapplication.view.ui.list.PREFERENCES_ITEM
import com.example.listapplication.view.ui.list.PREFERENCES_NAME

class SaveItemInteractor: Interactor<ItemListState, ItemListAction> {

    override suspend fun invoke(state: ItemListState, action: ItemListAction): ItemListAction {
        if (action is ItemListAction.SaveItem) {
            action.context
                .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(PREFERENCES_ITEM, action.item.id)
                .apply()
            return ItemListAction.ItemSaved
        }
        return ItemListAction.Error(IllegalArgumentException("Wrong action: $action"))
    }

    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.SaveItem
    }

}