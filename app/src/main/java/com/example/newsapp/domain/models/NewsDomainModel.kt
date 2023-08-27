package com.example.newsapp.domain.models

data class NewsDomainModel(
    val articles: List<ArticleDomainModel>?,
    val status: String,
    val totalResults: Int
)
