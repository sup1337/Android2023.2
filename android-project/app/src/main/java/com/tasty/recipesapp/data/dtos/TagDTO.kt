package com.tasty.recipesapp.data.dtos
import com.google.gson.annotations.SerializedName

data class TagDTO(
    @SerializedName("display_name")
    val displayName: String,
    val type: String,
    @SerializedName("root_tag_type")
    val rootTagType: String,
    val name: String,
    val id: Int,
)
