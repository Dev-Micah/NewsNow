package com.micah.newsnow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun HomePage(
    newsViewModel: NewsViewModel
){
    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
           items(articles){article ->
               Text(text="${article.title}")

           }

        }
    }
}
