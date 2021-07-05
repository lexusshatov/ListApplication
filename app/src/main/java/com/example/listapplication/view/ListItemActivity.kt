package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.listapplication.R
import com.example.listapplication.databinding.ActivityMainBinding
import com.example.listapplication.intent.ListItemHandler
import com.example.listapplication.intent.ListItemViewEvent
import com.example.listapplication.intent.ListItemViewState
import com.example.listapplication.intent.Store
import com.example.listapplication.model.background.MyService
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.data.ItemHolder

val TAG: String = ListItemActivity::class.java.name

class ListItemActivity : AppCompatActivity(), ListItemView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Item>
    private val store = Store(this)

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

        ItemHolder.items.observe(this, {
            try {
                if (it.isEmpty() || it == null)
                    store.render(ListItemViewState.NoItems)
                else
                    store.render(ListItemViewState.Loaded(it))
            } catch (e: Exception){
                store.render(ListItemViewState.Error(e.stackTraceToString()))
            }
        })

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                store.obtainEvent(ListItemViewEvent.ClickOnItem(it))
            }
        }

        store.obtainEvent(ListItemViewEvent.ViewStarted)
    }

    private fun saveItem(item: Item){
        getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .edit()
            .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), item.id)
            .apply()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showItems(items: List<Item>) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun startBackground() {
        startForegroundService(Intent(this, MyService::class.java))
    }

    override fun startItemActivity(item: Item) {
        saveItem(item)
        startActivity(Intent(this, ItemActivity::class.java))
    }

}