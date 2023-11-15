package com.tasty.recipesapp.ui.recipes

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.RepositoryProvider
import java.io.IOException

class RecipeListViewModel : ViewModel() {

    // LiveData to hold the list of InstructionModels
    // This should be changed to recipes
    private val _instructionModels = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = _instructionModels

    // Function to load data from the repository
    // Context should be removed
    fun loadInstructionData(context: Context) {
        val data = RepositoryProvider.instructionsRepository.getAll(context)
        _instructionModels.value = data
    }
}
