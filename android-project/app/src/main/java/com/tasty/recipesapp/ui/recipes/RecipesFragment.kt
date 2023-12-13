package com.tasty.recipesapp.ui.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.adapters.OnItemClickListener
import com.tasty.recipesapp.data.adapters.RecipesAdapter
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment(),OnItemClickListener {

    private lateinit var binding: FragmentRecipesBinding
    private val recipeViewModel: RecipesViewModel by viewModels()
    private val adapter: RecipesAdapter = RecipesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipesRecyclerView = binding.recipesRecyclerView

        recipesRecyclerView.adapter = adapter

        recipeViewModel.loadRecipeModel(requireContext())

        recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
            adapter.setData(recipes)
        }

        binding.recipesRecyclerView.layoutManager = LinearLayoutManager(this.context)

    }

    override fun onItemClick(item: RecipeModel) {
        navigateToRecipeDetail(item)
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        findNavController()
            .navigate(R.id.action_recipesFragment_to_recipeDetailsFragment,
                bundleOf("recipeId" to recipe.id))
    }

}