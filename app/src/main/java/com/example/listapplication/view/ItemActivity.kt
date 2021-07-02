package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.listapplication.R
import com.example.listapplication.databinding.ActivityItemBinding
import com.example.listapplication.model.database.Item
import com.example.listapplication.model.database.repository.ItemHolder
import com.example.listapplication.viewmodel.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class ItemActivity : AppCompatActivity(), Serializable  {
    private lateinit var binding: ActivityItemBinding
    private val itemViewModel by viewModel<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val itemId = this.getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .getInt(getString(R.string.APP_PREFERENCES_ITEM_ID), -1)
        val item = ItemHolder.getItemById(itemId)
        if (item != null){
            showData(item)
        } else {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        itemViewModel.item.observe(this, {
            Log.d("Observer", "item id: ${it?.id}")
            if (it != null){
                showData(it)
            }
        })
    }

    private fun showData(item: Item){
        binding.itemId.text = item.id.toString()
        binding.itemName.text = item.name
        binding.itemDescription.text = item.description
    }
}