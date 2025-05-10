package com.example.etax.presentation.screens.movies.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.etax.domain.model.Result

@Composable
fun MovieItem(movie: Result) {
    // Define how each movie item should look
    Text(text = movie.title?:"")
}