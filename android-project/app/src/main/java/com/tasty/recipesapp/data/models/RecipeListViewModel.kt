package com.tasty.recipesapp.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeListViewModel: ViewModel() {
    private val _instructionModels = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = _instructionModels

    fun loadInstructionsData(context: Context) {
        val data = RepositoryProvider.instructionnsRepository.getAll()
        _instructionModels.value = data

    }
}