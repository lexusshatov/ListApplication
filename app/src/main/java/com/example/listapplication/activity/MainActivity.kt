package com.example.listapplication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.listapplication.R
import com.example.listapplication.data.Item
import com.example.listapplication.data.ItemHolder
import com.example.listapplication.databinding.ActivityMainBinding
import com.example.listapplication.mvi.Action
import com.example.listapplication.mvi.State
import com.example.listapplication.mvi.Store
import com.example.listapplication.service.MyService
import com.github.c5fr7q.jungle.MviView
import java.io.File
import java.io.FileWriter
import java.io.Serializable

const val EXTRA_NAME_ITEM = "com.example.listapplication.LIST_ITEM"

class MainActivity : AppCompatActivity(), MviView<State, Action> {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,
        ArrayList<Item>())
    private lateinit var store: Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        store.attach(this)


        val intentStartService = Intent(this, MyService::class.java)
        startForegroundService(intentStartService)

        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
                    .edit()
                    .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), it.id)
                    .apply()
                val intentStartItemActivity = Intent(this, ItemActivity::class.java)
                intentStartItemActivity.putExtra(EXTRA_NAME_ITEM, it.id)
                processAction(Action.StartItemActivity(intentStartItemActivity))
                 }
        }
    }

    override fun processAction(action: Action) {
        when (action) {
            is Action.ShowError ->
                Toast.makeText(
                    this,
                    action.error,
                    Toast.LENGTH_SHORT
                ).show()
            is Action.StartItemActivity ->  {
                startActivity(action.intent)
            }

        }
    }

    override fun render(state: State) {
        binding.listView.visibility = if (state.items.isEmpty())
            View.GONE else View.VISIBLE
        adapter.apply {
            clear()
            addAll(ItemHolder.items)
            notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        store.detach()
    }
}