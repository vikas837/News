package com.example.newsapp.data.model
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
)