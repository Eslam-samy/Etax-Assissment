package com.example.etax.data.mappers

import com.example.etax.data.model.ResultDto
import com.example.etax.domain.model.Result

fun ResultDto.toDomain(): Result {
    return Result(
        adult,
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage
    )
}