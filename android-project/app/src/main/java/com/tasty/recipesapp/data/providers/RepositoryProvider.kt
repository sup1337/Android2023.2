package com.tasty.recipesapp.data.providers

import com.tasty.recipesapp.data.repositories.RecipeRepository
import com.tasty.recipesapp.data.repositories.InstructionRepository
object RepositoryProvider {
    val instructionsRepository: InstructionRepository = InstructionRepository()
    val recipeRepository: RecipeRepository = RecipeRepository()
}