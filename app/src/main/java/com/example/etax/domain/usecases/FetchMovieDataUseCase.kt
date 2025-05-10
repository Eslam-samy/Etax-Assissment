package com.example.etax.domain.usecases

import com.example.etax.domain.repository.MoviesRepository
import javax.inject.Inject

class FetchMovieDataUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke() {
        repository.getMovie()
    }
}
