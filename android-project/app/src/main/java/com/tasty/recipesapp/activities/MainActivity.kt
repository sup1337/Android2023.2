package com.tasty.recipesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.providers.RepositoryProvider
import com.tasty.recipesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation();
        RepositoryProvider.initialize(applicationContext)
    }
    private fun setupBottomNavigation() {
// When using FragmentContainerView the navController needs to be referenced using supportFragmentManager
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment. navController
// Find reference to bottom navigation view
        val navView: BottomNavigationView = binding.bottomNavigationView
// Hook your navigation controller to bottom navigation view
        navView.setupWithNavController (navController)
    }
}