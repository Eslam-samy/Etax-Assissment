package com.example.etax.domain.di

import com.example.etax.domain.repository.MoviesRepository
import com.example.etax.domain.usecases.CheckMoviesInDatabaseUseCase
import com.example.etax.domain.usecases.SetCacheValidationUseCase

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
    fun provideSetCacheValidationUseCase(repository: MoviesRepository): SetCacheValidationUseCase {
        return SetCacheValidationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCheckMoviesInDatabaseUseCase(
        repository: MoviesRepository
    ): CheckMoviesInDatabaseUseCase {
        return CheckMoviesInDatabaseUseCase(repository)
    }

}