package com.example.etax.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.etax.domain.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MoviesDataBase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MoviesDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDataBase::class.java,
                "movies_db"
            ).build()
        }
    }

    abstract fun getMovieDao(): MovieDao
}