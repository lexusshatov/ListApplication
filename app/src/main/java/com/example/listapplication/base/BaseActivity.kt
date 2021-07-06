package com.natife.example.mviexample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel<*, *>, VB : ViewBinding> : AppCompatActivity() {

    abstract val viewModelProvider: () -> VM
    protected val viewModel by lazy {
        viewModelProvider()
    }
    abstract val viewBindingProvider: () -> VB
    private var bindingInternal: VB? = null
    protected val binding: VB
        get() = bindingInternal!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInternal = viewBindingProvider()
        val view = binding.root
        setContentView(view)
    }
}