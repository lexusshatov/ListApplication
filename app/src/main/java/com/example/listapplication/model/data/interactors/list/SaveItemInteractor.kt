package com.example.listapplication.model.data.interactors.list

import android.content.Context
import android.content.SharedPreferences
import com.example.listapplication.base.Interactor
import com.example.listapplication.view.ui.list.ItemListAction
import com.example.listapplication.view.ui.list.ItemListState
import com.example.listapplication.view.ui.list.PREFERENCES_ITEM

class SaveItemInteractor(val preferences: SharedPreferences): Interactor<ItemListState, ItemListAction> {

    override suspend fun invoke(state: ItemListState, action: ItemListAction): ItemListAction {
        if (action is ItemListAction.SaveItem) {
            preferences
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