package com.micah.newsnow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
                model = article.urlToImage?: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANgAAACUCAMAAADGZBfIAAAAS1BMVEXs7Ox2dnb09PTk5OTx8fGPj49xcXHb29uCgoK0tLSLi4tra2vU1NR9fX2IiIh5eXnMzMyYmJitra3BwcGmpqagoKC7u7tmZmb6+vrPX5F3AAAEfElEQVR4nO2biZKjIBBABRokCgIeif//pduAR5LZ2TFVu+Vi9auaUREzPrl6kFQVQRAEQRAEQRAEQRAEQRAEQRAEQRAEcQXqDzn7fg8ibpp9hO7F2fd8iLtSn4kpdT/7no8gAnqp9jAxdyihyITEW5W3w0gUk6WIaX4csAWJNfx4ft5dSQwg/1QXE+NmlHI0yexSYr3CIUG1fTS7kBj0jzyAPe78UmKi3YZmcSUxfn+sYu3AryQ27iXWX0qs38XuVxIDs1VFVUPZYrAMxstRWIqsDWX3ilD10/B8XDfRTLWNgKLFuGyV6uc9AcTIHg/Wi7IHaJhi+Txuz2m8EqIqPVa85X+o29d/k7dWV6oYDGydKdja2UtXUqgYGLvNgFifhUCYp/y8KVEMKr3P7ChnYvpsnBv3MitUrNuijKRmOMAdk9ppy1+m2Pg+E+dNTmr7NX+JYnD/MsOo1q6kvS21sUAx8O77WVIMgJdxrDgxqO0fvGL3n+c8ShMDwX6Y6lbJrDyx7qcpfNV6XqDY2P7gFd9GGChNDG4/akUzbaAssdkf8UKzrp5LEpP10XdkSgtZkFjXHH7314aSovsPIbFTITES+0+4rFgVPhcLZ9/zEWBoP13A0g7w8+eeD3ipP0L6Irzepg3/QX6CIIj/lPUN7NN234H9/ezTwcvevtkTX/KchTEmxUK1qeOir8oPg694PpHIkZLYD3g9DEMNy0V5Y3KO5RNFvrquPlhA97cBbW16uTC6wCtuGuuc7WIUYRE8yW4pm4/71nkshwm3Vo/4EKBnYzx5cw2A11bH60TnPP6Kl3f9H//2PxbTOk3njjZwMNY2Yy+txZQQQmebIIcs5rQMQRpMd3oag2ZTXFClsphNYtpKLMhVTAapmT4tMkaxRusqi1WTk2KeYXJBAOChGualFXnX1Bz34W6tn+d5wA28i0mHRSSaJMYMPicZq8FZYl0fWM+j2FxLFud1oWY6hn4ixFU31YtYrrJxnQfW0Tcx65k2sIp5bI2DZmcVGbaxAZ9+DVHMa5a/GKBdrICvYt0QewcxqbR0AJvXJN7EmJhYeBaD+KRO6htR7A7STouYyw9Ysi9isfNwGtY0uP9OzGB7rbpNrDpZbMaeYbg5FOu+LzGrwxSmpxJT03tVZGhqtQhPJdacW2I8uKnPbWxY2pj5vo3JJCZzG5sWMZ7EeN24fmRZDFIbO0drEQNhO6mXXpHHTfraw7tYiigGbJTAYXCxfxlUZziv8blkMUBH7BtXMS/ZdF6viGL4zJnWcRzDoWgcQxrHfi9WVRPrpn7SaWjGjqKJ+ZnBqtixuK4Kx0WbxjGsuR1rzhvHnLqlcIEprGJgujiBofP8hZDb4inwrc4r0QHNIimoiN0e0mHpYCtsUYx75RSKpTe89syvKC3BIEZ6KVYEDATNGuPVRqxNX5jtG2Jc+MGLZU0B1B7jRsifkFLMHivCibHiNlnxJbp/Ovd1H5734SUH/CfRPUEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQf5Nf28BBiHjkfYEAAAAASUVORK5CYII=",
                contentDescription = "Article Image",
                modifier = Modifier.size(80.dp)
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