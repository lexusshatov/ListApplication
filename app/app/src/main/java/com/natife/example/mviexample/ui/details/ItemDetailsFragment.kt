package com.natife.example.mviexample.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natife.example.mviexample.base.BaseFragment
import com.natife.example.mviexample.data.interactors.details.GetItemByIdInteractor
import com.natife.example.mviexample.databinding.FragmentItemDetailsBinding
import com.natife.example.mviexample.util.createViewModel

class ItemDetailsFragment : BaseFragment<ItemDetailsViewModel, FragmentItemDetailsBinding>() {
    override val viewModelProvider = {
        createViewModel {
            ItemDetailsViewModel(
                interactors = setOf(
                    GetItemByIdInteractor()
                ),
                itemId = itemId
            )
        }
    }
    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentItemDetailsBinding =
        { inflater, container ->
            FragmentItemDetailsBinding.inflate(inflater, container, false)
        }
    private val itemId by lazy {
        arguments?.getInt(KEY_ITEM_ID, -1) ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.loadItem()
    }

    private fun renderState(newState: ItemDetailsState) {
        newState.item?.also { item ->
            with(binding) {
                textItemId.text = "Item id: ${item.id}"
                textItemName.text = "Item name: ${item.name}"
            }
        }
    }

    companion object {

        private const val KEY_ITEM_ID = "item_id"

        fun newInstance(itemId: Int) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ITEM_ID, itemId)
            }
        }
    }
}