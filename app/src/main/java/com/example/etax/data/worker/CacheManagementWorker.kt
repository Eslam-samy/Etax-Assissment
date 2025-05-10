package com.example.etax.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.etax.data.local.MovieDao
import com.example.etax.data.remote.ApiService
import com.example.etax.utils.isCacheStale
import com.example.etax.utils.shared_pref.PrefKeys
import com.example.etax.utils.shared_pref.PrefUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


const val CACHE_MANAGEMENT_WORK_NAME = "CACHE_MANAGEMENT_WORK_NAME"

@HiltWorker

class CacheManagementWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : CoroutineWorker(
    context, params
) {
    override suspend fun doWork(): Result {
        val lastUpdateTime =
            PrefUtils.getFromPrefs(context, PrefKeys.LAST_CACHE_UPDATE_TIME, 0L) as Long
        return try {
            if (lastUpdateTime.isCacheStale()) {
                movieDao.deleteAllMovies()
            }

            Result.success() // Successful execution
        } catch (e: Exception) {
            e.printStackTrace() // Log the exception
            Result.failure() // Mark the work as failed if an exception occurs
        }
    }
}