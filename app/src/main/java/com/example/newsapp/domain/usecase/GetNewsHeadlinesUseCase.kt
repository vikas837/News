package com.example.newsapp.domain.usecase

import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Flow<Resource<NewsDomainModel>> = newsRepository.getNewsHeadlines(country, page)
}