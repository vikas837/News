package com.example.newsapp.presentation.newslist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.domain.models.NewsDomainModel
import com.example.newsapp.presentation.common.Constants
import com.example.newsapp.presentation.progressbar.ProgressBarComponent
import com.example.newsapp.presentation.common.ViewState
import com.example.newsapp.presentation.viewmodel.NewsViewModel

@Composable
fun NewsListRoute(navController: NavController) {
    val viewModel = hiltViewModel<NewsViewModel>()

    LaunchedEffect(key1 = 0) {
        viewModel.getNewsHeadlines("us", 1)
    }

    when(val viewState = viewModel.viewState.collectAsState().value) {
        is ViewState.StateSuccess -> {
            NewsListScreen(navController, (viewState.result as NewsDomainModel).articles as ArrayList)
        }
        is ViewState.StateLoading -> {
            ProgressBarComponent()
        }
        is ViewState.StateError -> {
            Toast.makeText(LocalContext.current, Constants.defaultErrorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}