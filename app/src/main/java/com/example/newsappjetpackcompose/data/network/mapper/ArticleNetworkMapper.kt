package com.example.newsappjetpackcompose.data.network.mapper

import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.util.NetworkMapper

class ArticleNetworkMapper: NetworkMapper<ArticleNetwork, ArticleDomain> {
    override fun mapToDomainModel(dto: ArticleNetwork): ArticleDomain {
        val sectionName = dto.sectionName
        val webUrl = dto.webUrl
        val publicationDate = dto.webPublicationDate?.substring(0, 10)

        val fields = dto.fields

        val author = if(fields?.byline.isNullOrEmpty()) "Unknown Author" else fields?.byline
        val webTitle = if(fields?.headline.isNullOrEmpty()) "Unknown Title" else fields?.headline
        val thumbnailUrl = if(fields?.thumbnail.isNullOrEmpty()) "No image available" else fields?.thumbnail

        return ArticleDomain(
            webTitle ?: "Title Unknown",
            sectionName ?: "Section Unknown",
            author ?: "Author Unknown",
            publicationDate ?: "Date Unknown",
            webUrl ?: "",
            thumbnailUrl ?: "")
    }
}