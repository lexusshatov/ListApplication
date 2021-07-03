package com.example.listapplication.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listapplication.R
import com.example.listapplication.model.data.Item
import com.example.listapplication.databinding.ActivityItemBinding
import com.example.listapplication.model.data.ItemHolder
import java.io.Serializable

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val item = getSavedItem()
        if (item != null){
            showItem(item)
        } else {
            returnToListActivity()
        }
    }

    private fun getSavedItem(): Item? {
        getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE)
            .getInt(getString(R.string.APP_PREFERENCES_ITEM_ID), -1).let {
                return ItemHolder.getItemById(it)
            }
    }

    private fun returnToListActivity(){
        val intent = Intent(this, ListItemActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showItem(item: Item){
        binding.itemId.text = item.id.toString()
        binding.itemName.text = item.name
        binding.itemDescription.text = item.description
    }

}