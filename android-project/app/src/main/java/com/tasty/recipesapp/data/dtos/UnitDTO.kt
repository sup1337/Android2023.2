package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class UnitDTO(
    val system: String,
    val name: String?,
    @SerializedName("display_plural")
    val namePlural: String?,
    @SerializedName("display_singular")
    val nameSingular: String?,
    val abbreviation: String?,
)
