package com.example.etax.domain.usecases

import com.example.etax.data.local.MovieDao
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckMoviesInDatabaseUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke() {
        repository.getMovie()
    }
}