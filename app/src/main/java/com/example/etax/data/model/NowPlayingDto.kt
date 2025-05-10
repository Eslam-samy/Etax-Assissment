package com.example.etax.data.model


import com.google.gson.annotations.SerializedName
data class NowPlayingDto(
    @SerializedName("dates")
    val dates: DatesDto = DatesDto(),
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<ResultDto> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)