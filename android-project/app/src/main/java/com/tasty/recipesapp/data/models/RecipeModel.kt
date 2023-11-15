package com.tasty.recipesapp.data.models

data class RecipeModel(
    val name: String,
    val description: String?,
    val thumbnailUrl: String,
    val numServings: Int,
    val cookTimeMinutes: Int?,
    val nutrition: NutritionModel,
    val instructions: List<InstructionModel>,
    val sections: List<SectionModel>,
    val tags: List<TagModel>,
    val topics: List<TopicModel>,
    val credits: List<CreditModel>,
    val userRatings: UserRatingModel,
)