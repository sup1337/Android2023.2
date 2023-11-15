package com.tasty.recipesapp.data.models

data class ComponentModel(
    val rawText: String,
    val ingredient: IngredientModel,
    val measurements: List<MeasurementModel>,
)
