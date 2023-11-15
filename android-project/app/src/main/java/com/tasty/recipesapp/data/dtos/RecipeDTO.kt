package com.tasty.recipesapp.data.dtos
import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    val name: String,
    val tags: List<TagDTO>,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("user_ratings")
    val userRatings: UserRatingDTO,
    val sections: List<SectionDTO>,
    val nutrition: NutritionDTO,
    val topics: List<TopicDTO>,
    val instructions: List<InstructionDTO>,
    val credits: List<CreditDTO>,
    @SerializedName("num_servings")
    val numServings: Int,
    val description: String?,
    @SerializedName("cook_time_minutes")
    val cookTimeMinutes: Int?,
)
