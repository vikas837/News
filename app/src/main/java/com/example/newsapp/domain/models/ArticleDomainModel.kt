package com.example.newsapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDomainModel(
    val author: String?,
    val publishedAt: String,
    val title: String,
    val url: String
) : Parcelable
