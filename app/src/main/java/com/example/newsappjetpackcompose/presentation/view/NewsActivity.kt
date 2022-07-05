package com.example.newsappjetpackcompose.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.newsappjetpackcompose.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setObservers()
    }

    private fun setObservers(){
        val searchTerm = intent.getStringExtra("search")
        Log.d("NewsActivity", "The search term is $searchTerm")
    }
}