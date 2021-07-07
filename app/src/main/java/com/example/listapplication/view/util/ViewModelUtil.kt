package com.example.listapplication.view.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listapplication.base.BaseActivity
import com.example.listapplication.base.BaseViewModel

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