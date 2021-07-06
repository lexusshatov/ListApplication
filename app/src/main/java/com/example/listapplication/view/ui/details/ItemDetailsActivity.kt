package com.example.listapplication.view.ui.details

import android.os.Bundle
import com.example.listapplication.databinding.ActivityItemBinding
import com.natife.example.mviexample.base.BaseActivity
import com.natife.example.mviexample.data.interactors.details.GetItemByIdInteractor
import com.natife.example.mviexample.data.model.Item
import com.natife.example.mviexample.ui.details.ItemDetailsState
import com.natife.example.mviexample.ui.details.ItemDetailsViewModel
import com.natife.example.mviexample.util.createViewModel

const val EXTRA_ITEM_ID: String = "extra_item_id"

class ItemDetailsActivity : BaseActivity<ItemDetailsViewModel, ActivityItemBinding>() {
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
    override val viewBindingProvider: () -> ActivityItemBinding =
    {
        ActivityItemBinding.inflate(layoutInflater)
    }
    private val itemId by lazy {
        intent.getIntExtra(EXTRA_ITEM_ID, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this, {
            renderState(it)
        })
        viewModel.loadItem()
    }

    private fun renderState(newState: ItemDetailsState) {
        newState.item?.also { item ->
            with(binding) {
                itemId.text = "Item id: ${item.id}"
                itemName.text = "Item name: ${item.name}"
            }
        }
    }
}