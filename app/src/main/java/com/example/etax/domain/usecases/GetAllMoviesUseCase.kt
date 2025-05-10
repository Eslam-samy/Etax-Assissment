package com.example.etax.domain.usecases

import com.example.etax.domain.model.Result
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Result>> =
        repository.getAllMovies() // Returns a flow of movies from the repository
}