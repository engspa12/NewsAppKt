package com.example.newsappjetpackcompose.data.helper

interface NetworkMapper<Dto, DomainModel> {

    fun mapToDomainModel(dto: Dto): DomainModel
}
