package com.natife.example.mviexample.ui.details

import com.natife.example.mviexample.base.BaseViewModel
import com.natife.example.mviexample.base.Interactor

class ItemDetailsViewModel(
    interactors: Set<Interactor<ItemDetailsState, ItemDetailsAction>>,
    itemId: Int
) : BaseViewModel<ItemDetailsState, ItemDetailsAction>(
    interactors = interactors,
    reducer = ItemDetailsReducer(itemId)
) {

    fun loadItem() {
        action(ItemDetailsAction.Load)
    }
}