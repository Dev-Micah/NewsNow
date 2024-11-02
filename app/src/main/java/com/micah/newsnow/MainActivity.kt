package com.micah.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.micah.newsnow.ui.theme.NewsNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        setContent {
            NewsNowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(
                       modifier = Modifier.padding(innerPadding)
                           .fillMaxSize()
                   ){
                      Text(text = "NEWS NOW",
                          modifier = Modifier.align(Alignment.CenterHorizontally),
                          color = Color.Red,
                          fontSize = 25.sp,
                          fontFamily = FontFamily.Serif
                          )
                       HomePage(newsViewModel)
                   }
                }
            }
        }
    }
}