package com.example.newsapp.data.repository.datasource

import com.example.newsapp.data.model.NewsList
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<NewsList>
}