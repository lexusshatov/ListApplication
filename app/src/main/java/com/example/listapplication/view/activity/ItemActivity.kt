package com.example.listapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listapplication.databinding.ActivityItemBinding
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.intent.state.ItemViewState
import com.example.listapplication.model.intent.state.State
import com.example.listapplication.view.StateView
import com.example.listapplication.view.viewmodel.ItemViewModel

const val EXTRA_ITEM_ID: String = "extra_item_id"

class ItemActivity : AppCompatActivity(), StateView {
    private lateinit var binding: ActivityItemBinding
    private lateinit var viewModel: ItemViewModel
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)
        viewModel = ItemViewModel(itemId)

        viewModel.state.observe(this, {
            Log.d(TAG, it.toString())
            render(it)
        })
        viewModel.requestData()
    }

    private fun showItem(item: Item){
        binding.itemId.text = item.id.toString()
        binding.itemName.text = item.name
        binding.itemDescription.text = item.description
    }

    override fun render(state: State) {
        when (state){
            is ItemViewState.Loading -> {
                binding.progressBar.visibility = ProgressBar.VISIBLE
            }
            is ItemViewState.Loaded -> {
                binding.progressBar.visibility = ProgressBar.GONE
                showItem(state.item)
            }
            is ItemViewState.Error -> {
                Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
            }
            is ItemViewState.NoFound -> {
                val intent = Intent(this, ListItemActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}