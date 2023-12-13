package com.tasty.recipesapp.data.models

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String? = null,
    val thumbnailUrl: String? = null,
    val numServings: Int? = 0,
    val cookTimeMinutes: Int? = 0,
    val nutrition: NutritionModel? = null,
    val instructions: List<InstructionModel>? = null,
    val sections: List<SectionModel>? = null,
    val tags: List<TagModel>? = null,
    val topics: List<TopicModel>? = null,
    val credits: List<CreditModel>? = null,
    val userRatings: UserRatingModel? = null,
)