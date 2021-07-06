package com.natife.example.mviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natife.example.mviexample.ui.list.ItemListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragments, ItemListFragment.newInstance())
            .commit()
    }
}