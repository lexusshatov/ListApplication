package com.example.listapplication.view.ui.details

import com.example.listapplication.base.BaseViewModel
import com.example.listapplication.base.Interactor

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