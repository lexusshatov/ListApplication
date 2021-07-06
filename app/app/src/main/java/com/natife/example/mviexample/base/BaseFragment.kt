package com.natife.example.mviexample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel<*, *>, VB : ViewBinding> : Fragment() {

    abstract val viewModelProvider: () -> VM
    protected val viewModel by lazy {
        viewModelProvider()
    }
    abstract val viewBindingProvider: (LayoutInflater, ViewGroup?) -> VB
    private var bindingInternal: VB? = null
    protected val binding: VB
        get() = bindingInternal!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingInternal = viewBindingProvider(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInternal = null
    }
}