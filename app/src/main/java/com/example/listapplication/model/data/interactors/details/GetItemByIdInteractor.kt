package com.example.listapplication.model.data.interactors.details

import com.example.listapplication.base.Interactor
import com.example.listapplication.view.ui.details.ItemDetailsAction
import com.example.listapplication.view.ui.details.ItemDetailsState
import com.example.listapplication.view.util.ItemHolder

class GetItemByIdInteractor : Interactor<ItemDetailsState, ItemDetailsAction> {

    override suspend fun invoke(
        state: ItemDetailsState,
        action: ItemDetailsAction
    ): ItemDetailsAction {
        return if (action is ItemDetailsAction.Load) {
            try {
                ItemDetailsAction.ItemLoaded(ItemHolder.findItemById(state.itemId))
            } catch (e: Exception) {
                ItemDetailsAction.Error(e)
            }
        } else {
            ItemDetailsAction.Error(IllegalArgumentException("Wrong action: $action"))
        }
    }

    override fun canHandle(action: ItemDetailsAction): Boolean {
        return action is ItemDetailsAction.Load
    }

}