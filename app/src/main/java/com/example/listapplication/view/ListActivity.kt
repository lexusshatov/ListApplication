package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.listapplication.R
import com.example.listapplication.databinding.ActivityListBinding
import com.example.listapplication.model.data.Item
import com.example.listapplication.presenter.ListPresenter
import com.example.listapplication.presenter.background.MyService

const val EXTRA_NAME_ITEM = "com.example.listapplication.LIST_ITEM"

class ListActivity : AppCompatActivity(), ListContractView {
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: ArrayAdapter<Item>
    private lateinit var presenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = ListPresenter()
        presenter.attachView(this)
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            ArrayList<Item>())
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                presenter.onItemListSelected(it)
            }
        }
        presenter.viewIsReady()
    }

    override fun showList(items: List<Item>) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun startBackground() {
        val intentStartService = Intent(this, MyService::class.java)
        startForegroundService(intentStartService)
    }

    override fun saveItem(item: Item) {
        getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .edit()
            .putInt(getString(R.string.APP_PREFERENCES_ITEM_ID), item.id)
            .apply()
    }

    override fun startItemActivity(item: Item) {
        val intentStartItemActivity = Intent(this, ItemActivity::class.java)
        intentStartItemActivity.putExtra(EXTRA_NAME_ITEM, item.id)
        startActivity(intentStartItemActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}