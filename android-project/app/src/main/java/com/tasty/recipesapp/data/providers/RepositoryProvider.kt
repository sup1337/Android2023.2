package com.tasty.recipesapp.data.providers

import com.tasty.recipesapp.data.repositories.InstructionsRepository

object RepositoryProvider {
    val instructionsRepository: InstructionsRepository = InstructionsRepository()
}