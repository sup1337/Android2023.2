package com.tasty.recipesapp.data.entitys

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)
    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>
    @Query("DELETE FROM recipe WHERE internalId = :id")
    suspend fun deleteRecipeById(id: Long)
}
