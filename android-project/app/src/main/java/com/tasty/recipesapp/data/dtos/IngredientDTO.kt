package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class IngredientDTO(
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("display_plural")
    val displayPlural: String,
    val id: Int,
    @SerializedName("display_singular")
    val displaySingular: String,
    @SerializedName("updated_at")
    val updatedAt: Int,
    val name : String,
)