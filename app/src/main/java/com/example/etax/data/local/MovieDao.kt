package com.example.etax.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.etax.domain.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Result)

    @Query("SELECT * FROM Result ORDER BY releaseDate DESC LIMIT :limit OFFSET :offset")
    fun getPagedMovies(limit: Int, offset: Int): Flow<List<Result>>

    @Query("SELECT * FROM Result ORDER BY id ASC")
    fun getAllMoviesPaged(): PagingSource<Int, Result>

    @Query("DELETE FROM Result")
    suspend fun deleteAllMovies()

    @Query("SELECT COUNT(*) FROM Result")
    suspend fun getMoviesCount(): Int
}