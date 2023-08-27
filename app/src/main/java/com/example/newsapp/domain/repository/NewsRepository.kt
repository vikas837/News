package com.example.newsapp.domain.repository

import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.NewsDomainModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Flow<Resource<NewsDomainModel>>
}