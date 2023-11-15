package com.tasty.recipesapp.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.data.providers.RepositoryProvider

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