package com.example.newsapp.presentation

import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.ArticleDomainModel
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest

class NewsViewModelTests {
    @MockK
    lateinit var getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase

    private lateinit var sut: NewsViewModel

    @get:Rule
    val mainCoroutineRule = CoroutineScopeTestWatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        sut = NewsViewModel(getNewsHeadlinesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetNewsHeadlines_with_success() {
        runTest {
            coEvery { getNewsHeadlinesUseCase.execute("us", 1) } returns (flow {
                emit(Resource.Success(getAPIResponseList()))
            })
            sut.getNewsHeadlines("us", 1)
        }

        assertThat(sut.viewState.value.result).isEqualTo(true)
        assertThat((sut.viewState.value.result as NewsDomainModel).totalResults).isEqualTo(10)
        assertThat((sut.viewState.value.result as NewsDomainModel).articles?.count()).isEqualTo(1)
    }

    @Test
    fun testGetNewsHeadlines_with_Fail() {
        runTest {
            coEvery { getNewsHeadlinesUseCase.execute("us", 1) } returns (flow {
                emit(Resource.Error(500, "Failed to retrieve data"))
            })
            sut.getNewsHeadlines("us", 1)
        }
        assertThat((sut.viewState.value.result as NewsDomainModel).articles).isEqualTo(null)
        assertThat(sut.viewState.value.msg).isEqualTo("Failed to retrieve data")
    }

    @Test
    fun testGetNewsHeadlines_with_loading() {
        runTest {
            coEvery { getNewsHeadlinesUseCase.execute("us", 1) } returns (flow {
                emit(Resource.Loading())
            })
            sut.getNewsHeadlines("us", 1)
        }
        assertTrue(sut.viewState.value.isLoading)
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