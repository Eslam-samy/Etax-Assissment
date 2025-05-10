package com.example.etax.domain.usecases

import com.example.etax.domain.repository.MoviesRepository
import javax.inject.Inject

class SetCacheValidationUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke() {
        repository.setPeriodicCacheValidation() // This calls your repository method to start cache validation periodically
    }
}
