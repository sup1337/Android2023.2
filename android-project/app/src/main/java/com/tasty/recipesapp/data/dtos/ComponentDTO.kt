package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ComponentDTO(
    @SerializedName("extra_comment")
    val extraComment: String?,
    val ingredient: IngredientDTO,
    val id: Int,
    val position: Int,
    val measurements: List<MeasurementDTO>,
    @SerializedName("raw_text")
    val rawText: String,
)