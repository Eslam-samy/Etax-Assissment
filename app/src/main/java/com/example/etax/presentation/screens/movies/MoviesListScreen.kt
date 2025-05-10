package com.example.etax.presentation.screens.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.etax.presentation.screens.movies.components.EmptyComponent
import com.example.etax.presentation.screens.movies.components.ErrorComponent
import com.example.etax.presentation.screens.movies.components.LoadingComponent
import com.example.etax.presentation.screens.movies.components.MovieItem
import com.example.etax.presentation.screens.movies.viewmodel.MoviesListViewModel

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    movieViewModel: MoviesListViewModel = hiltViewModel() // Inject ViewModel using Hilt
) {
    val movies = movieViewModel.moviesFlow.collectAsLazyPagingItems()
//    val movies = movieViewModel.pagedMovies.collectAsLazyPagingItems()


    when {
        movies.loadState.refresh is LoadState.Loading && movies.itemCount == 0 -> {
            LoadingComponent() // Show full-screen loader only during first load
        }

        movies.loadState.refresh is LoadState.Error -> {
            ErrorComponent(
                message = (movies.loadState.refresh as LoadState.Error).error.message ?: ""
            )
        }

        movies.itemCount == 0 -> {
            EmptyComponent()
        }

        else -> {
            LazyColumn {
                items(
                    count = movies.itemCount,
                    key = { index ->
                        movies[index]?.id ?: index
                    }
                ) { index ->
                    MovieItem(
                        movie = movies[index]!!,
                    )
                }
            }
        }
    }
}