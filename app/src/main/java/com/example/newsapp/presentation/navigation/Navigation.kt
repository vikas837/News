package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.domain.models.ArticleDomainModel
import com.example.newsapp.presentation.newslist.NewsListRoute
import com.example.newsclient.presentation.news.newsDetail.DetailScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            NewsListRoute(navController = navController)
        }
        composable (route = Screen.DetailScreen.route) {
            val articleDomainModel = navController.previousBackStackEntry?.savedStateHandle?.get<ArticleDomainModel>(
                "article"
            )
            DetailScreen(navController = navController, article = articleDomainModel)
        }

    }

}
