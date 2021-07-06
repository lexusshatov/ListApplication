package com.natife.example.mviexample.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.natife.example.mviexample.base.BaseActivity
import com.natife.example.mviexample.base.BaseViewModel

// 1. Это расширяющая функция? Если да, то почему описываем её в другом файле,
// а не в файле класса, если у нас есть доступ к нему?
inline fun <reified T : BaseViewModel<*, *>> BaseActivity<T, *>.createViewModel(
    crossinline viewModelInitializer: () -> T
): T {
    return ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModelInitializer() as T
        }
    }).get(T::class.java)
}