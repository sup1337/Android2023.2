package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class UserRatingDTO(
    @SerializedName("count_negative")
    val countNegative: Int,
    @SerializedName("count_positive")
    val countPositive: Int,
    val score: Double,
)
