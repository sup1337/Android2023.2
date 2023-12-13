package com.tasty.recipesapp.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entitys.RecipeEntity
import com.tasty.recipesapp.data.models.ComponentModel
import com.tasty.recipesapp.data.models.IngredientModel
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.InstructionTime
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.data.models.SectionModel
import com.tasty.recipesapp.data.providers.RepositoryProvider
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import kotlinx.coroutines.launch

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
    private var ingredients = mutableListOf<TextInputEditText>()
    private var instructions = mutableListOf<TextInputEditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addIngredientButton.setOnClickListener {
            addNewIngredient()
        }

        binding.addInstructionButton.setOnClickListener {
            addNewInstruction()
        }

        binding.saveButton.setOnClickListener {
            saveNewRecipe()
        }
    }

    private fun addNewIngredient() {
        val newTextField = TextInputEditText(requireContext())

        newTextField.hint = "#${ingredients.size + 1} Ingredient"

        newTextField.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        ingredients.add(newTextField)

        binding.ingredientsLayout.addView(newTextField)
    }

    private fun addNewInstruction() {
        val newTextField = TextInputEditText(requireContext())

        newTextField.hint = "#${instructions.size + 1} Instruction"

        newTextField.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        instructions.add(newTextField)

        binding.instructionsLayout.addView(newTextField)
    }

    private fun saveNewRecipe() {

        if (binding.recipeName.text.toString().isEmpty()) {
            binding.recipeName.hint = "Recipe name is required"
            return
        }

        val name = binding.recipeName.text.toString()
        val thumbnailUrl = binding.pictureUrl.text.toString()
        val description = binding.description.text.toString()
        val numServings = binding.numOfServings.text.toString().toInt()
        val cookTimeMinutes = binding.cookTime.text.toString().toInt()

        val sections = mutableListOf<SectionModel>()
        val components = mutableListOf<ComponentModel>()

        ingredients.forEach{ ing ->
            if(ing.text.toString().isNotEmpty()) {

                val ingredient = IngredientModel(ing.text.toString());
                components.add(ComponentModel(ing.text.toString(), ingredient, null))
            }
        }

        val instructionList = mutableListOf<InstructionModel>()
        var instructionCounter = 0

        instructions.forEach { ins ->
            if(ins.text.toString().isNotEmpty()) {

                instructionCounter++
                instructionList.add(InstructionModel(instructionCounter, ins.text.toString(), InstructionTime(0,0)))
            }
        }

        sections.add(SectionModel(components))

        val newRecipe = RecipeModel(0, name = name, description = description, thumbnailUrl = thumbnailUrl,  numServings = numServings, sections = sections, instructions = instructionList, cookTimeMinutes = cookTimeMinutes)

        val recipeEntity  = RecipeEntity(0, Gson().toJson(newRecipe, RecipeModel::class.java))

        lifecycleScope.launch{
            RepositoryProvider.recipeRepository.insertRecipe(recipeEntity)
        }

        binding.recipeName.text?.clear()
        binding.pictureUrl.text?.clear()
        binding.description.text?.clear()

        binding.cookTime.text?.clear()

        ingredients.clear()
        instructions.clear()

        findNavController().navigate(R.id.action_newRecipeFragment_to_recipesFragment)
    }
}