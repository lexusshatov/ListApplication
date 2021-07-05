package com.example.listapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listapplication.databinding.ActivityMainBinding
import com.example.listapplication.model.intent.state.ListItemViewState
import com.example.listapplication.model.intent.SaveItemId
import com.example.listapplication.model.background.MyService
import com.example.listapplication.model.data.Item
import com.example.listapplication.model.intent.state.State
import com.example.listapplication.view.StateView
import com.example.listapplication.view.viewmodel.ListItemViewModel


const val PREFERENCES_NAME = "app_preferences"
const val PREFERENCES_ITEM = "app_preferences_item_id"

class ListItemActivity : AppCompatActivity(), StateView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Item>
    private val viewModel = ListItemViewModel()
    private val saveItemId = SaveItemId(this)
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        startForegroundService(Intent(this, MyService::class.java))
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ArrayList<Item>()
        )
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                saveItemId(it.id)
                val intentStartItemActivity = Intent(this, ItemActivity::class.java)
                intentStartItemActivity.putExtra(EXTRA_ITEM_ID, it.id)
                startActivity(intentStartItemActivity)
            }
        }

        viewModel.state.observe(this, {
            Log.d(TAG, it.toString())
            render(it)
        })
        viewModel.requestData()
    }

    private fun showItems(items: List<Item>){
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun render(state: State) {
        when (state) {
            is ListItemViewState.Loading -> {
                binding.progressBar.visibility = ProgressBar.VISIBLE
            }
            is ListItemViewState.Loaded -> {
                binding.progressBar.visibility = ProgressBar.GONE
                showItems(state.items)
            }
            is ListItemViewState.Error -> {
                Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
            }
            is ListItemViewState.NoItems -> {
                viewModel.requestData()
            }
        }
    }

}