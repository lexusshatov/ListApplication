package com.example.listapplication.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapplication.model.intent.render.ListViewStateRender
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.model.intent.state.ListItemViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListItemViewModel: ViewModel() {
    private val stateRender = ListViewStateRender()
    private val mutableState = MutableLiveData<ListItemViewState>()
    val state: LiveData<ListItemViewState> = mutableState

    fun requestData(){
        viewModelScope.launch (Dispatchers.IO) {
            mutableState.postValue(stateRender(true, null, null))
            try {
                mutableState.postValue(stateRender(false, null, ItemHolder.items))
            } catch (e: Exception) {
                mutableState.postValue(stateRender(false, e.toString(), null))
            }
        }
    }

}