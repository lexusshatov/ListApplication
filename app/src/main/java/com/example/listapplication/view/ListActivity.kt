package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.listapplication.R
import com.example.listapplication.databinding.ActivityListBinding
import com.example.listapplication.model.database.Item
import com.example.listapplication.view.background.MyService
import com.example.listapplication.viewmodel.ListItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: ArrayAdapter<Item>
    private val listItemViewModel by viewModel<ListItemViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intentStartService = Intent(this, MyService::class.java)
        startForegroundService(intentStartService)

        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            ArrayList<Item>())
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                saveItem(it)
            }
            val intentStartItemActivity = Intent(this, ItemActivity::class.java)
            startActivity(intentStartItemActivity)
        }

        listItemViewModel.data.observe(this, {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }

    private fun saveItem(item: Item){
        getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .edit()
            .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), item.id)
            .apply()
    }

}