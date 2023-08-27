package com.example.newsapp.data.repository.datasourceImpl

import com.example.newsapp.data.api.NewsAPIService
import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val service: NewsAPIService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<NewsList> = service.getTopHeadlines(country, page)
}