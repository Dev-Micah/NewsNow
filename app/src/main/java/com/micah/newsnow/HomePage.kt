package com.micah.newsnow

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun HomePage(
    newsViewModel: NewsViewModel
){
    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        CategoriesBar(newsViewModel)
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
           items(articles){article ->
           ArticleItem(article)

           }

        }
    }
}
@Composable
fun ArticleItem(article: Article){
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = article.urlToImage?: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAMFBMVEXz9Pa5vsro6u729/ji5OnT1969ws21usjM0NjFydPw8fTR1Nz5+vre4OXIzNXt7vGvU6iIAAACq0lEQVR4nO3b23qiMBRAYcLmmBh4/7cdBascglrImE2/9d+N07FdsyEGi1kGAAAAAAAAAAAAAAAAAAAAAAAAAEAEcolDJHVJluW+jMMXNm2J5G0dT1dcUsZ4E1XtE7YUVdwYY3yyE8d2sVtMm6pF8nEwVRxjTZFoNFIO374p4nDDs5VpY3KJY5yzS9PyiIn0dMRE88djRGxu9+6xdMVYP+xtqnLfHktVTN/V9fBA3RZ7nk5TTG/q5wv5nj2WohjbTVpMtWM2emLET1uu24Lfnzd6YrJ2sWHMf72m6YnJF7vf6sVZ04c71cRIsYgx5eY/c10ffFxPzOqScytGytp0wdmoiclWk9k4zMTdForgbPTEyOICemttLsdFLzQbPTEXN48JnxfW3RfwOvD3emLuP8rrxcyWr2oVxcyXgMYGTvFpy202iy9RFHP903M2LnSQzVoCR5qmmOuK1lXmdgnQ+tBeZtGyXgV0xUhf+NtbxsFLT1uu3zBsZrPRFfOSW6UsZ3OemLIOxcxqzhIjGy2zFfosMZst0zXtJDHbLdMapTG2mO7M1mvyoua+kKuMkeskJheab1qeewGVMdnt4e7n0dDrS3g2CmNkaDGP2byby/DFw+/LFMY8fvqx5v1c9MbIcxJ124t8Mhe1MbOjquo/a1Eas5jEp7+L1hnz6tXxbDEfHlVniLE756Ix5sOV6wwxkvm9c9EXs/t8URizfuP8zDGH7tUihhhiiCHmf8TkzRGFqpjrNvOI4RkUxRxHTDRxY3TccOojfUpjXBBdqluBx29fHVrInobBVMnun+/vtzLF+YzG+D8Tvq/mG45ckYU16T4R1DdxU0I3bnyxxhmz/9p/qerSfrbJete1kXQ+dF/Hd3P6PJKUhxgAAAAAAAAAAAAAAAAAAABwSv8AkJ81XG/eK0EAAAAASUVORK5CYII=",
                contentDescription = "Article Image",
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.fillMaxSize()
                .padding(start= 8.dp)) {
                Text(text = article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                    )
                Text(
                    text = article.source.name,
                    maxLines = 1,
                    fontSize = 14.sp
                )

            }
        }
    }
}
@Composable
fun CategoriesBar(newsViewModel: NewsViewModel){
    var searchQuery by remember {
        mutableStateOf("")
    }

    var isSearchExpanded by remember {
        mutableStateOf(false)
    }

    val categoriesList = listOf("General", "Business", "Entertainment", "Health", "Science", "Sports", "Technology")
    Row(modifier = Modifier.fillMaxWidth()
        .horizontalScroll(rememberScrollState()),

        verticalAlignment = Alignment.CenterVertically) {

        if(isSearchExpanded){
            OutlinedTextField(
                modifier = Modifier.padding(8.dp)
                    .height(48.dp)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape),
                value = searchQuery,
                onValueChange = {searchQuery = it}

            )
        }else{
            IconButton(onClick = {
                isSearchExpanded = true

            }){
                Icon(
                 imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"


                )

            }
        }

        categoriesList.forEach{category ->
            Button(onClick = {
                newsViewModel.fetchNewsTopHeadlines(category)
            },
                modifier = Modifier.padding(4.dp),){
                Text(text = category)

            }
        }

    }

}