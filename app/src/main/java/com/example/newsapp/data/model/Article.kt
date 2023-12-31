package com.example.newsapp.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
): Serializable