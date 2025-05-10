package com.example.etax.domain.usecases

import com.example.etax.data.local.MovieDao
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckMoviesInDatabaseUseCase @Inject constructor(
    private val movieDao: MovieDao,
    private val repository: MoviesRepository
) {
    suspend operator fun invoke() {
        val movies = movieDao.getAllMovies().first() // Get all movies from the database
        if (movies.isEmpty()) {
            repository.getMovie() // Fetch movies from API if DB is empty
        } else {
            repository.setPeriodicWorkRequest() // Set periodic fetch if data exists
        }
    }
}