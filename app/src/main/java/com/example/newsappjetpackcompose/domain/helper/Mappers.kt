package com.example.newsappjetpackcompose.domain.helper

interface NetworkMapper<Dto, DomainModel> {

    fun mapToDomainModel(dto: Dto): DomainModel
}
