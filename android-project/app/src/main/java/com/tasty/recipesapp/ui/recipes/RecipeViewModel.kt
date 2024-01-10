package com.tasty.recipesapp.ui.recipes
import com.tasty.recipesapp.data.providers.RepositoryProvider
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.NutritionModel
import com.tasty.recipesapp.data.models.RecipeModel
import java.io.IOException
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.repositories.RecipeRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

// Itt lesznek az adatok tarolva
object RecipesViewModel : ViewModel() {

    private val _recipeModels = MutableLiveData<List<RecipeModel>>()
    val recipeModels: LiveData<List<RecipeModel>> = _recipeModels

    fun loadRecipeModel(context: Context) {

        viewModelScope.launch {
            val data = RepositoryProvider.recipeRepository.getAll(context);
            _recipeModels.postValue(data)
        }
    }
    fun getRecipeById(id: Int) : RecipeModel?{
        return _recipeModels.value?.find { it -> it.id == id }
    }
    fun getRecipesFromApi(filter: Filter? = null, search: String? = null, sort: Sort? = null, order: Order? = null) {
        viewModelScope.launch {

            var tags: String? = null

            tags = when (filter) {
                Filter.SEAFOOD -> "seafood"
                Filter.EASY -> "easy"
                else -> {
                    null
                }
            }

            val data = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "20", tags, search, sort, order)
            _recipeModels.postValue(data)
        }
    }


}