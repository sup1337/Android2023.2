package com.tasty.recipesapp.data.service

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 1794f19e26msh1ded00e1dc44fbbp16156ejsn981b9cc5c4eb",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null,
        @Query("q") search: String? = null
    ): RecipeResponse

}
