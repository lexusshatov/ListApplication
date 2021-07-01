package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.listapplication.R
import com.example.listapplication.databinding.ActivityListBinding
import com.example.listapplication.model.database.Item
import com.example.listapplication.model.database.repository.ItemHolder
import com.example.listapplication.viewmodel.ItemListViewModel
import com.example.listapplication.viewmodel.LoadingState
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

const val EXTRA_NAME_ITEM = "com.example.listapplication.LIST_ITEM"

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val itemViewModel by viewModel<ItemListViewModel>()
    private lateinit var adapter: ArrayAdapter<Item>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            ArrayList<Item>())
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val intentStartItemActivity = Intent(this, ItemActivity::class.java)
            adapter.getItem(position)?.let {
                intentStartItemActivity.putExtra(EXTRA_NAME_ITEM, it.id)
                getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
                    .edit()
                    .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), it.id)
                    .apply() }
            startActivity(intentStartItemActivity)
        }

        itemViewModel.data.observe(this, {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        })

        itemViewModel.loadingState.observe(this, {
            when(it.status){
                LoadingState.Status.FAILED -> Toast.makeText(baseContext, it.msg, Toast.LENGTH_SHORT).show()
                LoadingState.Status.RUNNING -> Toast.makeText(baseContext, "Loading", Toast.LENGTH_SHORT).show()
                LoadingState.Status.SUCCESS -> Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            }
        })
        /*
        val listItems = ItemHolder.items

        val intentStartService = Intent(this, MyService::class.java)
        startForegroundService(intentStartService)

        val


         */
    }

}