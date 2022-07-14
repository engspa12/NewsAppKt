package com.example.newsappjetpackcompose.presentation.view.appcompat.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.databinding.ActivityWelcomeBinding
import com.example.newsappjetpackcompose.presentation.view.compose.NewsComposeActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private var sortType = "relevance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSeachButton()
    }

    private fun setSeachButton(){
        binding.searchButton.setOnClickListener {
            val intent = Intent(this, NewsComposeActivity::class.java).apply {
                putExtra("search", binding.searchEt.text.toString().trim())
                putExtra("sort_type", sortType)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.relevant_news -> {
                sortType = "relevance"
                binding.searchButton.text = getString(R.string.search_relevant_news_text);
            }
            R.id.latest_news -> {
                sortType = "newest"
                binding.searchButton.text = getString(R.string.search_latest_news_text);
            }
        }
        return super.onOptionsItemSelected(item)
    }
}