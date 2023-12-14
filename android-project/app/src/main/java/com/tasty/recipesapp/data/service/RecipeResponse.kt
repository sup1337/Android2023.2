package com.tasty.recipesapp.data.service

import com.tasty.recipesapp.data.dtos.RecipeDTO

data class RecipeResponse(
    val count : Int,
    val results : List<RecipeDTO>
)
