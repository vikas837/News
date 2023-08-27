package com.example.newsapp.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel: ViewModel() {

    protected val viewStateFlow = MutableStateFlow<ViewState<Any>>(
        ViewState.StateLoading(true)
    )

    val viewState = viewStateFlow.asStateFlow()
}