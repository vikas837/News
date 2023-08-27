package com.example.newsapp.presentation.di

import com.example.newsapp.data.mapper.NewsListAPIResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsAPIResponseMapperModule {

    @Singleton
    @Provides
    fun providesNewsAPIResponseMapper(): NewsListAPIResponseMapper {
        return NewsListAPIResponseMapper()
    }
}