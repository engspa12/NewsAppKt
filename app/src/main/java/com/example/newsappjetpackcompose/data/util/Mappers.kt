package com.example.newsappjetpackcompose.data.util

interface NetworkMapper<Dto, DomainModel> {

    fun mapToDomainModel(dto: Dto): DomainModel
}
