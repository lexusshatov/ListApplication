package com.example.listapplication.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listapplication.model.data.Item
import com.example.listapplication.databinding.ActivityItemBinding
import java.io.Serializable

class ItemActivity : AppCompatActivity(), Serializable  {
    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemId = intent.extras?.get(EXTRA_NAME_ITEM) as? Int
        Log.d("ItemActivity", "item from intent: $itemId")
        if (itemId != null){
            val item = Item(itemId)
            binding.itemId.text = item.id.toString()
            binding.itemName.text = item.name
            binding.itemDescription.text = item.description
        } else {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}