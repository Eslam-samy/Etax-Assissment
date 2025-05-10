package com.example.etax.presentation.screens.movies.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.etax.domain.model.Result

@Composable
fun MoviesListComponent(movies: List<Result>) {
    // Display the list of movies (e.g., a LazyColumn or Grid)
    LazyColumn {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}