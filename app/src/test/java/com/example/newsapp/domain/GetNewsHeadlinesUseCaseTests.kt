package com.example.newsapp.domain

import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.ArticleDomainModel
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNewsHeadlinesUseCaseTests {
    private lateinit var sut: GetNewsHeadlinesUseCase
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        repository = mockk<NewsRepository>()
    }

    @Test
    fun testExecute() {
        runBlocking {
            coEvery { repository.getNewsHeadlines("us", 1) } returns (flow {
                emit(Resource.Success(getAPIResponseList()))
            })
            sut = GetNewsHeadlinesUseCase(repository)
            sut.execute("us", 1)
            coVerify { repository.getNewsHeadlines("us", 1) }
        }
    }

    private fun getAPIResponseList(): NewsDomainModel {
        val listOfArticles = listOf<ArticleDomainModel>(
            ArticleDomainModel(
                "name",
                "12/03/23",
                "This is the first Article",
                "www.google.com"
            )
        )
        return NewsDomainModel(
            listOfArticles,
            "10",
            10
        )
    }
}