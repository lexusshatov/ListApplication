package com.natife.example.mviexample.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.example.mviexample.util.LiveValueDelegate
import com.natife.example.mviexample.util.delegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<State, Action>(
    private val interactors: Set<Interactor<State, Action>>,
    private val reducer: Reducer<State, Action>
) : ViewModel() {

    private val mutableState = MutableLiveData(reducer.initialState)
    // 1. Для чего делегируем состояние?
    //private var stateValue = mutableState.value!!  - не работает
    //private var stateValue by LiveValueDelegate(mutableState)  - работает
    private var stateValue by mutableState.delegate()
    val state: LiveData<State> = mutableState

    // 2. Почему функция вызывается из главного потока?
    // 3. Может ли произойти так, что состояние модели изменится, а интерактор все еще будет
    // обрабатывать действие для старого состояния?
    @MainThread
    protected fun action(action: Action) {
        // обновление состояния
        stateValue = reducer.reduce(stateValue, action)
        // для каждого интерактора из набора интеракторов, который
        // может обрабатывать переданное действие в фоновом потоке
        // просим обработать это действие и полученное действие снова обрабатываем
        // до тех пор, пока не будет ни одного интерактора, который может обработать его
        interactors.filter { it.canHandle(action) }.forEach { interactor ->
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.invoke(stateValue, action)
                withContext(Dispatchers.Main) {
                    action(result)
                }
            }
        }
    }

}