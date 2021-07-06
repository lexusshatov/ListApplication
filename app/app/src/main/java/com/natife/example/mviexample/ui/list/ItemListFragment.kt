package com.natife.example.mviexample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.example.mviexample.R
import com.natife.example.mviexample.base.BaseFragment
import com.natife.example.mviexample.data.interactors.list.GetItemsInteractor
import com.natife.example.mviexample.data.interactors.list.TestInteractor
import com.natife.example.mviexample.data.model.Item
import com.natife.example.mviexample.databinding.FragmentItemListBinding
import com.natife.example.mviexample.ui.details.ItemDetailsFragment
import com.natife.example.mviexample.util.createViewModel

class ItemListFragment : BaseFragment<ItemListViewModel, FragmentItemListBinding>() {

    override val viewModelProvider = {
        createViewModel {
            ItemListViewModel(
                interactors = setOf(
                    GetItemsInteractor(),
                    TestInteractor()
                )
            )
        }
    }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentItemListBinding =
        { inflater, container ->
            FragmentItemListBinding.inflate(inflater, container, false)
        }
    private val adapter by lazy {
        ItemListAdapter {
            navigateToDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.loadItems()
    }

    private fun initViews() {
        with(binding) {
            recyclerItems.adapter = adapter
            recyclerItems.layoutManager = LinearLayoutManager(context)
            buttonTest.setOnClickListener {
                viewModel.test()
            }
        }
    }

    private fun renderState(newState: ItemListState) {
        adapter.submitList(newState.items)
    }

    private fun navigateToDetails(item: Item) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container_fragments, ItemDetailsFragment.newInstance(item.id))
            addToBackStack(null)
            commit()
        }

    }

    companion object {

        fun newInstance() = ItemListFragment()

    }

}