package com.tasty.recipesapp.ui.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private val recipeViewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel.loadInstructionModel(requireContext())
        recipeViewModel.instructionModels.observe(viewLifecycleOwner) { instructions ->
            for (instructionModel in instructions) {
                Log.d("Instructions", instructionModel.toString())
            }
        }

        recipeViewModel.loadRecipeModel(requireContext())
        recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
            for (recipeModel in recipes) {
                Log.d("Sample", recipeModel.toString())
            }
        }
    }


}