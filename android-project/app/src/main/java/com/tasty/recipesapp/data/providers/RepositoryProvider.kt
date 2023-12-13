package com.tasty.recipesapp.data.providers

import android.content.Context
import com.tasty.recipesapp.data.entitys.RecipeDao
import com.tasty.recipesapp.data.entitys.RecipeDatabase
import com.tasty.recipesapp.data.repositories.RecipeRepository
import com.tasty.recipesapp.data.repositories.InstructionRepository
object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao

    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao)
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }

    val instructionRepository: InstructionRepository = InstructionRepository()

}