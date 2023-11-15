package com.tasty.recipesapp.data.dtos

data class SectionDTO(
    val components: List<ComponentDTO>,
    val name: String?,
    val position: Int,
)
