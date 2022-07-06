package com.example.newsappjetpackcompose.presentation.view.appcompat.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappjetpackcompose.databinding.ActivityNewsBinding
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import com.example.newsappjetpackcompose.presentation.view.appcompat.adapter.ListArticlesAdapter
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
        setObserver()
        getListArticles()
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

    private fun setObserver(){

        viewModel.uiState.observe(this) { state ->
            when(state) {
                is ArticlesUIState.Success -> {
                    adapter.setList(state.data)
                    showListArticles()
                }
                is ArticlesUIState.Error -> {
                    binding.emptyView.text = state.errorMessage
                    showErrorMessage()
                }
                is ArticlesUIState.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun showListArticles(){
        binding.recyclerView.visibility = View.VISIBLE
        binding.emptyView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar(){
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showErrorMessage(){
        binding.recyclerView.visibility = View.GONE
        binding.emptyView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}