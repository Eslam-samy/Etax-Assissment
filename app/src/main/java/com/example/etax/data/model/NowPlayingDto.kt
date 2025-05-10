package com.example.etax.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingDto(
    @SerialName("dates")
    val dates: DatesDto = DatesDto(),
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<ResultDto> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)