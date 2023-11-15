package com.tasty.recipesapp.ui.recipes
import com.tasty.recipesapp.data.providers.RepositoryProvider
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.NutritionModel
import com.tasty.recipesapp.data.models.RecipeModel
import java.io.IOException
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.repositories.RecipeRepository
import org.json.JSONObject

// Itt lesznek az adatok tarolva
class RecipesViewModel : ViewModel() {

    private val _instructionModels = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = _instructionModels

    fun loadInstructionModel(context: Context) {
        val data = RepositoryProvider.instructionsRepository.getAll(context);
        _instructionModels.value = data
    }

    private val _recipeModels = MutableLiveData<List<RecipeModel>>()
    val recipeModels: LiveData<List<RecipeModel>> = _recipeModels

    fun loadRecipeModel(context: Context) {
        val data = RepositoryProvider.recipeRepository.getAll(context);
        _recipeModels.value = data
    }

}