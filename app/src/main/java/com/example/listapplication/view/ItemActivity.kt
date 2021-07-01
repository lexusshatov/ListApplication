package com.example.listapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listapplication.model.data.Item
import com.example.listapplication.databinding.ActivityItemBinding
import com.example.listapplication.model.data.ItemHolder
import com.example.listapplication.presenter.ItemPresenter
import java.io.Serializable

class ItemActivity : AppCompatActivity(), ItemContractView  {
    private lateinit var binding: ActivityItemBinding
    private lateinit var presenter: ItemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = ItemPresenter()
        presenter.attachView(this)
        val itemId = intent.extras?.get(EXTRA_NAME_ITEM) as? Int
        presenter.viewIsReady(itemId)
    }

    override fun showItem(item: Item) {
        binding.itemId.text = item.id.toString()
        binding.itemName.text = item.name
        binding.itemDescription.text = item.description
    }

    override fun backToStartActivity() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}