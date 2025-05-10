package com.example.etax.domain.repository

import com.example.etax.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovie()

    fun getAllMovies(): Flow<List<Result>>

    fun setPeriodicWorkRequest()

    fun setPeriodicCacheValidation()
}