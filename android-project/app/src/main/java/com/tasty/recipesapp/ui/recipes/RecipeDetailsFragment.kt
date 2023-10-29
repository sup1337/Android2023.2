package com.tasty.recipesapp.ui.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.tasty.recipesapp.ui.profile.ProfileFragment

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding

    companion object {
        const val TAG = "RecipeDetailsFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate method called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}