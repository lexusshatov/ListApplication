package com.natife.example.mviexample.ui.list

import android.content.Context
import com.natife.example.mviexample.data.model.Item

sealed class ItemListAction {

    object None : ItemListAction()
    object LoadItems : ItemListAction()
    data class ItemsLoaded(val items: List<Item>) : ItemListAction()
    data class Error(val error: Exception) : ItemListAction()
    data class SaveItem(val context: Context, val item: Item) : ItemListAction()
    object ItemSaved: ItemListAction()

    //For test purposes
    data class Test(val testData: String) : ItemListAction()

}
