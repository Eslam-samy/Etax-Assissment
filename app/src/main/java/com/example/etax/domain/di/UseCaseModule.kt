package com.example.etax.domain.di

import com.example.etax.data.local.MovieDao
import com.example.etax.domain.repository.MoviesRepository
import com.example.etax.domain.usecases.CheckMoviesInDatabaseUseCase
import com.example.etax.domain.usecases.FetchMovieDataUseCase
import com.example.etax.domain.usecases.GetAllMoviesUseCase
import com.example.etax.domain.usecases.SetCacheValidationUseCase
import com.example.etax.domain.usecases.SetPeriodicFetchUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchMovieDataUseCase(repository: MoviesRepository): FetchMovieDataUseCase {
        return FetchMovieDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetPeriodicFetchUseCase(repository: MoviesRepository): SetPeriodicFetchUseCase {
        return SetPeriodicFetchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetCacheValidationUseCase(repository: MoviesRepository): SetCacheValidationUseCase {
        return SetCacheValidationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCheckMoviesInDatabaseUseCase(
        movieDao: MovieDao,
        repository: MoviesRepository
    ): CheckMoviesInDatabaseUseCase {
        return CheckMoviesInDatabaseUseCase(movieDao, repository)
    }

    @Provides
    @Singleton
    fun provideGetAllMoviesUseCase(repository: MoviesRepository): GetAllMoviesUseCase {
        return GetAllMoviesUseCase(repository)
    }
}