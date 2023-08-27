package com.example.newsapp.presentation.newslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.domain.models.ArticleDomainModel
import com.example.newsapp.presentation.common.Constants
import com.example.newsapp.presentation.navigation.Screen

@Composable
fun NewsListScreen(navController: NavController, newsList: List<ArticleDomainModel>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = Constants.screenTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
        )

        if (newsList.isNotEmpty()) {
            LazyColumn {
                items(
                    items = newsList,
                    itemContent = {
                        NewsListItem(article = it) {

                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("article", it)
                            }
                            navController.navigate(Screen.DetailScreen.route)
                        }
                    }
                )
            }
        }
    }
}

