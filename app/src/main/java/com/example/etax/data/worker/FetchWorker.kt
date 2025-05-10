package com.example.etax.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.etax.BuildConfig
import com.example.etax.data.local.MovieDao
import com.example.etax.data.mappers.toDomain
import com.example.etax.data.remote.ApiService
import com.example.etax.utils.shared_pref.PrefKeys
import com.example.etax.utils.shared_pref.PrefUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


const val ONE_TIME_WORK_NAME = "ONE_TIME_WORK_NAME"

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : CoroutineWorker(
    context, params
) {

    override suspend fun doWork(): Result {
        return try {
            var page = PrefUtils.getFromPrefs(context, PrefKeys.LAST_FETCHED_PAGE, 1) as Int
            var totalPages = Int.MAX_VALUE
            val apiKey = BuildConfig.API_KEY
            while (page <= totalPages) {
                val response = apiService.getNowPlayingMovies(
                    apiKey = apiKey,
                    page = page
                )

                totalPages = response.totalPages
                movieDao.insertMovies(response.results.map { it.toDomain() })
                PrefUtils.saveToPrefs(context, PrefKeys.LAST_FETCHED_PAGE, page)
                page++
            }
            PrefUtils.saveToPrefs(
                context,
                PrefKeys.LAST_CACHE_UPDATE_TIME,
                System.currentTimeMillis()
            )
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}