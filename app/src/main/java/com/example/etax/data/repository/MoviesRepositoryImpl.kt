package com.example.etax.data.repository

import androidx.paging.PagingSource
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.etax.data.local.MovieDao
import com.example.etax.data.worker.CacheManagementWorker
import com.example.etax.data.worker.FetchWorker
import com.example.etax.domain.model.Result
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MoviesRepositoryImpl(
    private val workManager: WorkManager,
    private val movieDao: MovieDao
) : MoviesRepository {
    override suspend fun getMovie() {

        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        if (movieDao.getMoviesCount() == 0) {
            val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
                .setConstraints(constraint)
                .build()
            workManager.enqueue(
                workRequest
            )
        } else {
            val worRequest =
                PeriodicWorkRequestBuilder<FetchWorker>(15, TimeUnit.MINUTES)
                    .setConstraints(constraint)
                    .build()

            workManager.enqueueUniquePeriodicWork(
                "periodic_fetch_management",
                ExistingPeriodicWorkPolicy.UPDATE,
                worRequest
            )
        }


    }

    override suspend fun getMovieCount(): Int = withContext(Dispatchers.IO) {
        movieDao.getMoviesCount()
    }

    override fun getPagedMovies(
        page: Int
    ): Flow<List<Result>> =
        movieDao.getPagedMovies(20, (page - 1) * 20)

    override fun getMoviesPagingSource(): PagingSource<Int, Result> {
        // This returns Room's auto-invalidating PagingSource
        return movieDao.getAllMoviesPaged()
    }

    override fun setPeriodicCacheValidation() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val worRequest =
            PeriodicWorkRequestBuilder<CacheManagementWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraint)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "periodic_cache_management",
            ExistingPeriodicWorkPolicy.UPDATE,
            worRequest
        )
    }
}