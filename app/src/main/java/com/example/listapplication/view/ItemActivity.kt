package com.example.listapplication.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
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
        TODO("Not yet implemented")
    }

    override fun backToStartActivity() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}