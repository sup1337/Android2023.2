package com.tasty.recipesapp.ui.profile

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.data.providers.RepositoryProvider
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _recipeModel = MutableLiveData<List<RecipeModel>>()
    val recipeModel: MutableLiveData<List<RecipeModel>> = _recipeModel

    fun getAllMyRecipes() {
        viewModelScope.launch {
            val recipes = RepositoryProvider.recipeRepository.getFromDb()
            _recipeModel.postValue(recipes)
        }
    }

    fun deleteRecipeById(id : Long){
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.deleteRecipe(id)

            val updatedList = _recipeModel.value?.toMutableList()
            updatedList?.removeAll { it.id == id.toInt() }
            _recipeModel.postValue(updatedList!!)

        }
    }
}