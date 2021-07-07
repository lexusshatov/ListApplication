package com.example.listapplication.view.ui.list

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapplication.base.BaseActivity
import com.example.listapplication.databinding.ActivityListBinding
import com.example.listapplication.model.background.MyService
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.interactors.list.SaveItemInteractor
import com.example.listapplication.view.ui.details.EXTRA_ITEM_ID
import com.example.listapplication.view.ui.details.ItemDetailsActivity
import com.example.listapplication.model.data.interactors.list.GetItemsInteractor
import com.example.listapplication.view.util.createViewModel


const val PREFERENCES_NAME = "app_preferences"
const val PREFERENCES_ITEM = "app_preferences_item_id"

class ItemListActivity : BaseActivity<ItemListViewModel, ActivityListBinding>() {

    override val viewModelProvider = {
        createViewModel {
            ItemListViewModel(
                interactors = setOf(
                    GetItemsInteractor(),
                    SaveItemInteractor(preferences = getSharedPreferences(
                        PREFERENCES_NAME,
                        Context.MODE_PRIVATE)
                    )
                )
            )
        }
    }
    override val viewBindingProvider: () -> ActivityListBinding =
        {
            ActivityListBinding.inflate(layoutInflater)
        }
    private val adapter by lazy {
        ItemListAdapter {
            navigateToDetails(it)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startForegroundService(Intent(this, MyService::class.java))
        with (binding){
         recyclerView.adapter = adapter
         recyclerView.layoutManager = LinearLayoutManager(baseContext)
        }
        viewModel.state.observe(this, {
            renderState(it)
        })
        viewModel.loadItems()
    }

    private fun renderState(newState: ItemListState){
        adapter.submitList(newState.items)
    }

    private fun navigateToDetails(item: Item) {
        viewModel.saveItem(item)
        val intentStartActivity = Intent(this, ItemDetailsActivity::class.java)
        intentStartActivity.putExtra(EXTRA_ITEM_ID, item.id)
        startActivity(intentStartActivity)
    }

}