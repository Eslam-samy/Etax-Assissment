package com.example.etax.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.etax.domain.model.Result
import com.example.etax.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class MoviesPagingSource(
    private val searchKey: String,
    private val repository: MoviesRepository,
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val result = repository.getPagedMovies(page).first()
            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}