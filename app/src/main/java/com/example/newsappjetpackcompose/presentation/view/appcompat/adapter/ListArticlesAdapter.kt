package com.example.newsappjetpackcompose.presentation.view.appcompat.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.model.ArticleView

class ListArticlesAdapter(val listArticles: List<ArticleView>): RecyclerView.Adapter<ArticleViewHolder>() {

    private var innerList = listOf<ArticleView>()

    init {
        innerList = listArticles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = innerList[position]

        val author = currentArticle.author
        val sectionName = currentArticle.sectionName
        val title = currentArticle.title
        val releaseDate = currentArticle.releaseDate
        val thumbnailUrl = currentArticle.thumbnailUrl

        holder.titleTextView.text = title
        holder.sectionNameTextView.text = "$sectionName "

        if (author.isNotEmpty()) {
            holder.authorTextView.text = String.format("By %s", author);
        } else {
            holder.authorTextView.setText(R.string.by_unknown_author_text);
        }

        holder.releaseDateTextView.text = "$releaseDate "

        //Set thumbnail URL
        if(thumbnailUrl.isNotEmpty()){
            if(thumbnailUrl != holder.itemView.context.getString(R.string.no_image_available_thumbnail_field)) {
                Glide.with(holder.itemView.context)
                    .load(thumbnailUrl)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder_img))
                    .into(holder.thumbImageView)
            } else{
                holder.thumbImageView.setImageResource(R.drawable.no_image_available)
            }
        } else {
            holder.thumbImageView.setImageResource(R.drawable.no_image_available)
        }

        holder.itemView.setOnClickListener {
            //Get article URL
            val article = innerList[position]
            val url = article.webUrl
            val webPage = Uri.parse(url)

            //Show article in browser
            val intent = Intent(Intent.ACTION_VIEW, webPage)
            if (intent.resolveActivity(holder.itemView.context.packageManager) != null) {
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
       return innerList.size
    }

    fun setList(newList: List<ArticleView>){
        innerList = newList
        notifyDataSetChanged()
    }
}

class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val titleTextView: TextView = itemView.findViewById(R.id.title_article)
    val sectionNameTextView: TextView = itemView.findViewById(R.id.section_name_article)
    val authorTextView: TextView = itemView.findViewById(R.id.author_article)
    val releaseDateTextView: TextView = itemView.findViewById(R.id.date_published_article)
    val thumbImageView: ImageView = itemView.findViewById(R.id.thumb_image_view)

}