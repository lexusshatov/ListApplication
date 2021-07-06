package com.natife.example.mviexample.ui.details

import com.natife.example.mviexample.base.Reducer

class ItemDetailsReducer(itemId: Int) : Reducer<ItemDetailsState, ItemDetailsAction> {

    override val initialState = ItemDetailsState(
        itemId = itemId,
        item = null
    )

    override fun reduce(state: ItemDetailsState, action: ItemDetailsAction): ItemDetailsState {
        return when(action) {
            ItemDetailsAction.None -> state
            ItemDetailsAction.Load -> state
            is ItemDetailsAction.ItemLoaded -> state.copy(
                item = action.item
            )
            is ItemDetailsAction.Error -> state
        }
    }

}