package com.example.newsapp.data.repository

import com.example.newsapp.data.mapper.NewsListAPIResponseMapper
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object NetworkConstants {
    const val STATUS_NETWORK_ERROR: Int = 500
    const val STATUS_ERROR_DEFAULT: Int = 0
}

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsListAPIResponseMapper: NewsListAPIResponseMapper
) : NewsRepository {

    override suspend fun getNewsHeadlines(
        country: String,
        page: Int
    ): Flow<Resource<NewsDomainModel>> =
        flow {
            val response = newsRemoteDataSource.getTopHeadlines(country, page)
            when {
                response.isSuccessful -> emit(
                    Resource.Success(
                        newsListAPIResponseMapper.transformToDomainModel(response.body())
                    )
                )

                else -> emit(
                    Resource.Error(
                        NetworkConstants.STATUS_NETWORK_ERROR,
                        response.message()
                    )
                )
            }
        }
            .catch {
                emit(Resource.Error(NetworkConstants.STATUS_ERROR_DEFAULT, ""))
            }
            .flowOn(Dispatchers.IO)
}