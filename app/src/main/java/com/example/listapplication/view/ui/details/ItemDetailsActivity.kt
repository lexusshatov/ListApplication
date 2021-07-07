package com.example.listapplication.view.ui.details

import android.content.Intent
import android.os.Bundle
import com.example.listapplication.base.BaseActivity
import com.example.listapplication.databinding.ActivityItemBinding
import com.example.listapplication.model.data.interactors.details.GetItemByIdInteractor
import com.example.listapplication.view.ui.list.ItemListActivity
import com.example.listapplication.view.util.createViewModel

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
        checkSavedItemOrLeaveToListActivity()
        viewModel.state.observe(this, {
            renderState(it)
        })
        viewModel.loadItem()
    }

    private fun renderState(newState: ItemDetailsState) {
        newState.item?.also {
            with(binding) {
                itemId.text = "Item id: ${it.id}"
                itemName.text = "Item name: ${it.name}"
            }
        }
    }

    private fun checkSavedItemOrLeaveToListActivity(){
        if (itemId == -1) {
            startActivity(Intent(this, ItemListActivity::class.java))
            finish()
        }
    }
}