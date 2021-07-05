package com.example.listapplication.intent

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.listapplication.model.background.MyService
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.view.ItemActivity
import com.example.listapplication.view.ListItemView
import com.example.listapplication.view.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Store(val view: ListItemView): ListItemHandler {

    override fun render(state: ListItemViewState) {
        when (state) {
            is ListItemViewState.Loading -> {
                GlobalScope.launch {
                    ItemHolder.items.value
                }
            }
            is ListItemViewState.Loaded -> {
                makeAction(action = ListItemViewAction.ShowItems(state.items))
            }
            is ListItemViewState.NoItems -> {
                view.showToast("No items for load")
            }
            is ListItemViewState.Error -> {
                Log.d(TAG, state.error)
            }
        }
    }

    override fun obtainEvent(event: ListItemViewEvent) {
        when (event) {
            is ListItemViewEvent.ViewStarted -> {
                render(ListItemViewState.Loading)
                view.startBackground()
            }
            is ListItemViewEvent.ClickOnItem -> {
                makeAction(action = ListItemViewAction.ShowItem(event.item))
            }
        }
    }

    override fun makeAction(action: ListItemViewAction) {
        when (action) {
            is ListItemViewAction.ShowItems -> {
                view.showItems(action.items)
            }
            is ListItemViewAction.ShowItem -> {
                view.startItemActivity(action.item)
            }
        }
    }

}