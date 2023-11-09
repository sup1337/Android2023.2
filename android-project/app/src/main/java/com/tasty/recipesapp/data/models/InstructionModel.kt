package com.tasty.recipesapp.data.models

data class InstructionModel(
    val id: Int,
    val displayText: String,
    val time: Int,
)

data class InstructionTime(
    val startTime: Int,
    val endTime: Int
)
