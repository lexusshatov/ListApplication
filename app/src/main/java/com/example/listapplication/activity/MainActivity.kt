package com.example.listapplication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.listapplication.R
import com.example.listapplication.data.ItemHolder
import com.example.listapplication.databinding.ActivityMainBinding
import com.example.listapplication.service.MyService
import java.io.File
import java.io.FileWriter
import java.io.Serializable

const val EXTRA_NAME_ITEM = "com.example.listapplication.LIST_ITEM"

class MainActivity : AppCompatActivity(), Serializable {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listItems = ItemHolder.items

        val intentStartService = Intent(this, MyService::class.java)
        startForegroundService(intentStartService)

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            listItems)
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
    }

}