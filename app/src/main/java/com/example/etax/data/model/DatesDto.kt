package com.example.etax.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class DatesDto(
    @SerializedName("maximum")
    val maximum: String = "",
    @SerializedName("minimum")
    val minimum: String = ""
)