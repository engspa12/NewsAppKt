package com.example.newsappjetpackcompose.domain.util

import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.presentation.model.ArticleView

interface NetworkMapper<Dto, DomainModel> {
    fun mapToDomainModel(dto: Dto): DomainModel
}

fun ArticleDomain.toView(): ArticleView {
    return ArticleView(
        this.title,
        this.sectionName,
        this.author,
        this.releaseDate,
        this.webUrl,
        this.thumbnailUrl
    )
}