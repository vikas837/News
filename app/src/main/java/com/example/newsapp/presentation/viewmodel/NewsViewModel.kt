package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapp.presentation.common.BaseViewModel
import com.example.newsapp.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : BaseViewModel() {

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        fetchNewsHeadlines(country, page)
    }

    private suspend fun fetchNewsHeadlines(country: String, page: Int) {
        getNewsHeadlinesUseCase.execute(country, page).collect {
            when (it) {
                is Resource.Error -> viewStateFlow.value =
                    ViewState.StateError(
                        NewsDomainModel(articles = null, "", 0),
                        it.message ?: "",
                        it.errorCode
                    )

                is Resource.Success ->
                    it.data?.let { result ->
                        viewStateFlow.value = ViewState.StateSuccess(result)
                    } ?: kotlin.run {
                        viewStateFlow.value =
                            ViewState.StateError(
                                NewsDomainModel(articles = null, "", 0),
                                it.message ?: "",
                                it.errorCode
                            )
                    }
                is Resource.Loading -> viewStateFlow.value = ViewState.StateLoading(true)
            }
        }
    }
}