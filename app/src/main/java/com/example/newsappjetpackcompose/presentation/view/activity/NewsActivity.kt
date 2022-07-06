package com.example.newsappjetpackcompose.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappjetpackcompose.databinding.ActivityNewsBinding
import com.example.newsappjetpackcompose.presentation.view.adapter.ListArticlesAdapter
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val viewModel:NewsViewModel by viewModels()
    private lateinit var adapter: ListArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setRecyclerView()
        setObservers()
        getListArticles()

        binding.progressBar.visibility = View.GONE
    }

    private fun getListArticles() {
        val searchTerm = intent.getStringExtra("search") ?: ""
        val sortType = intent.getStringExtra("sort_type") ?: ""

        viewModel.getNews(searchTerm, sortType)
    }

    private fun setRecyclerView(){
        adapter = ListArticlesAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun setObservers(){

        viewModel.listNewsArticles.observe(this) { data ->
            adapter.setList(data)
        }

        viewModel.errorMessage.observe(this) { message ->
            binding.emptyView.text = message
        }
    }
}