package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName
data class NutritionDTO(
    val fiber: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    val protein: Int,
    val fat: Int,
    val calories: Int,
    val sugar: Int,
    val carbohydrates: Int,
)