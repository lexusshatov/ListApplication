package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listapplication.R
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.databinding.ActivityMainBinding
import com.example.listapplication.model.ListItemView
import com.example.listapplication.model.ListItemViewAction
import com.example.listapplication.model.ListItemViewEvent
import com.example.listapplication.model.ListItemViewState
import com.example.listapplication.model.background.MyService
import kotlinx.coroutines.*
import java.lang.Exception

val TAG: String = ListItemActivity::class.java.name

class ListItemActivity : AppCompatActivity(), ListItemView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Item>
    private val items: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ArrayList<Item>()
        )
        setContentView(view)
        binding.listView.adapter = adapter

        items.observe(this, {
            try {
                if (it.isEmpty() || it == null)
                    render(ListItemViewState.NoItems)
                else
                    render(ListItemViewState.Loaded(it))
            } catch (e: Exception){
                render(ListItemViewState.Error(e.stackTraceToString()))
            }
        })

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                obtainEvent(ListItemViewEvent.ClickOnItem(it))
            }
        }

        obtainEvent(ListItemViewEvent.ViewStarted)
    }

    private fun saveItem(item: Item){
        getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .edit()
            .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), item.id)
            .apply()
    }

    fun showItems(items: List<Item>) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun render(state: ListItemViewState) {
        when (state) {
            is ListItemViewState.Loading -> {
                GlobalScope.launch {
                    items.postValue(ItemHolder.items)
                }
            }
            is ListItemViewState.Loaded -> {
                makeAction(action = ListItemViewAction.ShowItems(state.items))
            }
            is ListItemViewState.NoItems -> {
                Toast.makeText(this, "No items for load", Toast.LENGTH_LONG).show()
            }
            is ListItemViewState.Error -> {
                Log.d(TAG, state.error)
            }
        }
    }

    override fun obtainEvent(event: ListItemViewEvent) {
        when (event) {
            is ListItemViewEvent.ViewStarted -> {
                render(ListItemViewState.Loading)
                startForegroundService(Intent(this, MyService::class.java))
            }
            is ListItemViewEvent.ClickOnItem -> {
                makeAction(action = ListItemViewAction.ShowItem(event.item))
            }
        }
    }

    override fun makeAction(action: ListItemViewAction) {
        when (action) {
            is ListItemViewAction.ShowItems -> {
                showItems(action.items)
            }
            is ListItemViewAction.ShowItem -> {
                saveItem(action.item)
                startActivity(Intent(this, ItemActivity::class.java))
            }
        }
    }
}