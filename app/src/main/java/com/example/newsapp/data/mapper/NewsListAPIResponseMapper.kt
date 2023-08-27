package com.example.newsapp.data.mapper

import com.example.newsapp.data.model.NewsList
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.models.ArticleDomainModel

class NewsListAPIResponseMapper {

    fun transformToDomainModel(response: NewsList?): NewsDomainModel =
        response?.let {
            val articlePresentable = transformArticleToArticleDomainModel(it)
            NewsDomainModel(
                articlePresentable,
                it.status,
                it.totalResults
            )
        } ?: NewsDomainModel(articles = listOf<ArticleDomainModel>(), "0", -1)

    private fun transformArticleToArticleDomainModel(response: NewsList): ArrayList<ArticleDomainModel> {
        val articles: ArrayList<ArticleDomainModel> = arrayListOf<ArticleDomainModel>()
        for (item in response.articles) {
            articles.add(
                ArticleDomainModel(
                    item.author,
                    item.publishedAt,
                    item.title,
                    item.url
                )
            )
        }
        return articles
    }
}