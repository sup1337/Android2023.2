package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.adapters.OnItemClickListener
import com.tasty.recipesapp.data.adapters.ProfileAdapter
import com.tasty.recipesapp.data.adapters.onDeleteClickListener
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), OnItemClickListener, onDeleteClickListener {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val adapter: ProfileAdapter = ProfileAdapter(this, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myRecipesRecyclerView = binding.myRecipesRecyclerView

        myRecipesRecyclerView.adapter = adapter

        viewModel.getAllMyRecipes()

        viewModel.recipeModel.observe(viewLifecycleOwner) { recipes ->
            adapter.setData(recipes)
        }

        binding.myRecipesRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onItemClick(item: RecipeModel) {
       findNavController()
            .navigate(
                R.id.action_profileFragment_to_profileRecipesDetailsFragment,
                bundleOf("recipeId" to item.id)
            )
    }

    override fun onDeleteClick(id: Long) {
        viewModel.deleteRecipeById(id)
    }

}