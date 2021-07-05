package com.example.listapplication.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.model.intent.state.ItemViewState
import com.example.listapplication.model.intent.render.ItemViewStateRender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(private val itemId: Int): ViewModel() {
    private val stateRender = ItemViewStateRender()
    private val mutableState = MutableLiveData<ItemViewState>()
    val state: LiveData<ItemViewState> = mutableState

    fun requestData(){
        viewModelScope.launch (Dispatchers.IO) {
            mutableState.postValue(stateRender(true, null, null))
            try {
                mutableState.postValue(stateRender(false, null, ItemHolder.getItemById(itemId)))
            } catch (e: Exception) {
                mutableState.postValue(stateRender(false, e.toString(), null))
            }
        }
    }
}