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
    private var sort: Sort? = null
    private var order: Order? = null
    private var filter: Filter? = null
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_newRecipeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipesRecyclerView = binding.recipesRecyclerView

        recipesRecyclerView.adapter = adapter

        binding.searchButton.setOnClickListener {
            onSearchButtonClick()
        }

        //recipeViewModel.loadRecipeModel(requireContext())
        recipeViewModel.getRecipesFromApi()

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
    private fun onSearchButtonClick() {
        val filterGroup = binding.filterGroup
        val sortGroup = binding.sortGroup
        val orderGroup = binding.orderGroup

        when (filterGroup.checkedRadioButtonId) {
            binding.radioButton2.id -> filter = Filter.SEAFOOD
            binding.radioButton1.id -> filter = Filter.EASY
        }

        when (sortGroup.checkedRadioButtonId) {
            binding.sortOption1.id -> sort = Sort.NAME
            binding.sortOption2.id -> sort = Sort.RATING
        }

        when (orderGroup.checkedRadioButtonId) {
            binding.orderASC.id -> order = Order.ASC
            binding.orderDESC.id -> order = Order.DESC
        }

        if (binding.searchBar.query.isNotEmpty()) {
            query = binding.searchBar.query.toString()
        }

        recipeViewModel.getRecipesFromApi(filter, query, sort, order)

    }

}