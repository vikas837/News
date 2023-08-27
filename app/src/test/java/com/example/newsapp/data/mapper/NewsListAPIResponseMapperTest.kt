package com.example.newsapp.data.mapper

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.model.Source
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

class NewsListAPIResponseMapperTest : TestCase() {

    private lateinit var newsListAPIResponseMapper: NewsListAPIResponseMapper

    override fun setUp() {
        super.setUp()
        newsListAPIResponseMapper = NewsListAPIResponseMapper()
    }

    fun testTransform_ToDomainModel_With_NullObject() {
        val model = newsListAPIResponseMapper.transformToDomainModel(null)

        assertThat(model.totalResults).isEqualTo(-1)
        assertThat(model.articles).isEmpty()
        assertThat(model.status).isEqualTo("0")
    }

    fun test_Transform_ToDomainModel_With_Success_Response() {
        val transformedModel = newsListAPIResponseMapper.transformToDomainModel(getAPIResponseList())
        assertThat(transformedModel.status).isEqualTo("10")
        assertThat(transformedModel.totalResults).isEqualTo(10)
        assertThat(transformedModel.articles?.count()).isEqualTo(1)
    }

    private fun getAPIResponseList(): NewsList {
        val listOfArticles = listOf<Article>(
            Article(
                "name",
                "12/03/23",
                Source("1", "test"),
                "This is the first Article",
                "www.google.com"
            )
        )
        val newsListResponse = NewsList(
            listOfArticles,
            "10",
            10

        )
        return newsListResponse
    }
}