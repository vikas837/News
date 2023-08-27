package com.example.newsapp.presentation.di

import com.example.newsapp.data.mapper.NewsListAPIResponseMapper
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesNewsRepositoryInstance(
        newsRemoteDatasource: NewsRemoteDataSource,
        newsListAPIResponseMapper: NewsListAPIResponseMapper
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDatasource, newsListAPIResponseMapper)
    }
}