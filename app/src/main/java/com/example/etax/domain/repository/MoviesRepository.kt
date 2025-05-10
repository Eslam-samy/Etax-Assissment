package com.example.etax.domain.repository

import androidx.paging.PagingSource
import com.example.etax.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovie()
    suspend fun getMovieCount() :Int

    fun getPagedMovies(
        page: Int
    ): Flow<List<Result>>

    fun setPeriodicCacheValidation()

    fun getMoviesPagingSource(): PagingSource<Int, Result>
}