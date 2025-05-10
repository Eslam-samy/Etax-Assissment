package com.example.etax.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity()
data class Result(
    val adult: Boolean = false,
    val backdropPath: String = "",
    @PrimaryKey
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)