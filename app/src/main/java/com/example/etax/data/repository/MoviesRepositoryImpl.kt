package com.example.etax.data.repository

import androidx.compose.ui.util.trace
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.etax.data.local.MovieDao
import com.example.etax.data.worker.CacheManagementWorker
import com.example.etax.data.worker.FetchWorker
import com.example.etax.data.worker.PeriodicWorker
import com.example.etax.domain.model.Result
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class MoviesRepositoryImpl(
    private val workManager: WorkManager,
    private val movieDao: MovieDao
) : MoviesRepository {
    override fun getMovie() {
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraint)
            .build()

        workManager.enqueue(
            workRequest
        )
    }

    override fun getAllMovies(): Flow<List<Result>> = movieDao.getAllMovies()

    override fun setPeriodicWorkRequest() {
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val worRequest =
            PeriodicWorkRequestBuilder<PeriodicWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "periodic_fetch_management",
            ExistingPeriodicWorkPolicy.UPDATE,
            worRequest
        )
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