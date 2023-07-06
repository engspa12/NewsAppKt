package com.example.newsappjetpackcompose.data.network.mapper

import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.util.NetworkMapper

class ArticleNetworkMapper: NetworkMapper<ArticleNetwork, ArticleDomain> {

    companion object {
        const val TITLE_UNKNOWN = "Title Unknown"
        const val SECTION_UNKNOWN = "Section Unknown"
        const val AUTHOR_UNKNOWN = "Author Unknown"
        const val DATE_UNKNOWN = "Date Unknown"
        const val NO_IMAGE_AVAILABLE = "No image available"
    }
    override fun mapToDomainModel(dto: ArticleNetwork): ArticleDomain {
        val sectionName = dto.sectionName
        val webUrl = dto.webUrl
        val publicationDate = dto.webPublicationDate?.substring(0, 10)

        val fields = dto.fields

        val author = if(fields?.byline.isNullOrEmpty()) AUTHOR_UNKNOWN else fields?.byline
        val webTitle = if(fields?.headline.isNullOrEmpty()) TITLE_UNKNOWN else fields?.headline
        val thumbnailUrl = if(fields?.thumbnail.isNullOrEmpty()) NO_IMAGE_AVAILABLE else fields?.thumbnail

        return ArticleDomain(
            webTitle ?: TITLE_UNKNOWN,
            sectionName ?: SECTION_UNKNOWN,
            author ?: AUTHOR_UNKNOWN,
            publicationDate ?: DATE_UNKNOWN,
            webUrl.orEmpty(),
            thumbnailUrl.orEmpty())
    }
}