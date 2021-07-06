package com.natife.example.mviexample.data.interactors.details

import com.natife.example.mviexample.base.Interactor
import com.natife.example.mviexample.ui.details.ItemDetailsAction
import com.natife.example.mviexample.ui.details.ItemDetailsState
import com.natife.example.mviexample.util.ItemHolder

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