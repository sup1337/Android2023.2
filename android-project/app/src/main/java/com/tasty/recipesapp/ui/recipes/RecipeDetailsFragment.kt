package com.tasty.recipesapp.ui.recipes

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.tasty.recipesapp.ui.profile.ProfileFragment

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding
    private val recipeListViewModel: RecipesViewModel by viewModels()
    private lateinit var recipe: RecipeModel
    private var ingredients = mutableListOf<TextView>()
    private var instructions = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = arguments?.getInt("recipeId")

        if (recipeId != null) {

                recipe = recipeListViewModel.getRecipeById(recipeId)!!

            Glide.with(this)
                .load(recipe.thumbnailUrl)
                .into(binding.pictureUrl)

            binding.recipeName.text = recipe.name
            binding.recipeDescription.text = recipe.description
//            binding.numOfServings.text = recipe.numServings.toString()
//            binding.cookTime.text = "${recipe.cookTimeMinutes.toString()} min"

            recipe.sections?.forEach { sectionModel ->
                sectionModel.components.forEach { it ->

                    val textView = TextView(requireContext())

                    textView.layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )

                    textView.text = "${ingredients.size + 1}. ${it.ingredient.name}"


                    ingredients.add(textView)
                }
            }

            recipe.instructions?.forEach { it ->
                val textView = TextView(requireContext())

                textView.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )

                textView.text = "${instructions.size + 1}. ${it.displayText}\n"


                instructions.add(textView)
            }

            ingredients.forEach {
                binding.ingredientsLayout.addView(it)
            }

            instructions.forEach {
                binding.instructionsLayout.addView(it)
            }

        }
    }
}