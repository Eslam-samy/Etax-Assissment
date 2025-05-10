package com.example.etax.presentation.screens.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.etax.data.paging.MoviesPagingSource
import com.example.etax.domain.model.Result
import com.example.etax.domain.repository.MoviesRepository
import com.example.etax.domain.usecases.CheckMoviesInDatabaseUseCase
import com.example.etax.domain.usecases.SetCacheValidationUseCase
import com.example.etax.presentation.screens.movies.ui_state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val setCacheValidationUseCase: SetCacheValidationUseCase,
    private val checkMoviesInDatabaseUseCase: CheckMoviesInDatabaseUseCase,
    private val repository: MoviesRepository,
) : ViewModel() {

    private val _moviesFlow =
        MutableStateFlow<PagingData<Result>>(PagingData.empty())
    val moviesFlow: StateFlow<PagingData<Result>> =
        _moviesFlow.asStateFlow()

    init {
        // Start with checking the movies in the database when ViewModel is initialized
        viewModelScope.launch {
            checkMoviesInDatabaseUseCase()
            setCacheValidationUseCase()
        }
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    searchKey = "",
                    repository = repository,
                )
            }
        ).flow
            .cachedIn(viewModelScope)
            .onEach { pagingData ->
                _moviesFlow.value = pagingData
            }
            .launchIn(viewModelScope)

    }

}