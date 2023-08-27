package com.example.newsapp.presentation.common

sealed class ViewState<T>(val result: T, val msg: String, val isLoading: Boolean, val errorCode: Int=-1) {
    class StateSuccess<T>(data: T) : ViewState<T>(data, "", false)
    class StateError<T>(result: T, msg: String, errorCode: Int) : ViewState<T>(result, msg, false, errorCode)
    class StateLoading<T>(result: T) : ViewState<T>(result, "", true)
}