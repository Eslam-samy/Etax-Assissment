package com.example.etax.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.etax.data.local.MovieDao
import com.example.etax.data.local.MoviesDataBase
import com.example.etax.data.remote.ApiService
import com.example.etax.data.repository.MoviesRepositoryImpl
import com.example.etax.domain.repository.MoviesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            )
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MoviesDataBase {
        return MoviesDataBase.getInstance(context)
    }

    @Provides
    fun provideMovieDao(moviesDataBase: MoviesDataBase): MovieDao {
        return moviesDataBase.getMovieDao()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(workManager: WorkManager, movieDao: MovieDao): MoviesRepository {
        return MoviesRepositoryImpl(workManager, movieDao)

    }


}