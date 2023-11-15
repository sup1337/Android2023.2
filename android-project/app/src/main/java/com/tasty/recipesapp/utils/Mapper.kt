package com.tasty.recipesapp.utils

import com.tasty.recipesapp.data.dtos.ComponentDTO
import com.tasty.recipesapp.data.dtos.CreditDTO
import com.tasty.recipesapp.data.dtos.IngredientDTO
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.MeasurementDTO
import com.tasty.recipesapp.data.dtos.NutritionDTO
import com.tasty.recipesapp.data.dtos.SectionDTO
import com.tasty.recipesapp.data.dtos.TagDTO
import com.tasty.recipesapp.data.dtos.TopicDTO
import com.tasty.recipesapp.data.dtos.UnitDTO
import com.tasty.recipesapp.data.dtos.UserRatingDTO
import com.tasty.recipesapp.data.models.ComponentModel
import com.tasty.recipesapp.data.models.CreditModel
import com.tasty.recipesapp.data.models.IngredientModel
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.InstructionTime
import com.tasty.recipesapp.data.models.MeasurementModel
import com.tasty.recipesapp.data.models.NutritionModel
import com.tasty.recipesapp.data.models.SectionModel
import com.tasty.recipesapp.data.models.TagModel
import com.tasty.recipesapp.data.models.TopicModel
import com.tasty.recipesapp.data.models.UnitModel
import com.tasty.recipesapp.data.models.UserRatingModel

fun CreditDTO.toModel() = CreditModel(
    name = this.name,
)

fun IngredientDTO.toModel() = IngredientModel(
    name = this.name,
    displaySingular = this.displaySingular,
    displayPlural = this.displayPlural,
)

fun InstructionDTO.toModel() = InstructionModel(
    id = this.id,
    displayText = this.displayText,
    time = InstructionTime(this.startTime, this.endTime)
)

fun NutritionDTO.toModel() = NutritionModel(
    calories = this.calories,
    fiber = this.fiber,
    protein = this.protein,
    fat = this.fat,
    sugar = this.sugar,
    carbohydrates = this.carbohydrates,
)

fun TagDTO.toModel() = TagModel(
    displayName = this.displayName,
    type = this.type,
)

fun TopicDTO.toModel() = TopicModel(
    name = this.name,
)

fun UnitDTO.toModel() = UnitModel(
    nameSingular = this.nameSingular,
    namePlural = this.namePlural,
    abbreviation = this.abbreviation,
)

fun UserRatingDTO.toModel() = UserRatingModel(
    countNegative = this.countNegative,
    countPositive = this.countPositive,
)

fun MeasurementDTO.toModel() = MeasurementModel(
    unit = this.unit.toModel(),
    quantity = this.quantity,
)

fun ComponentDTO.toModel() = ComponentModel(
    rawText = this.rawText,
    ingredient = this.ingredient.toModel(),
    measurements = this.measurements.map { it.toModel() },
)

fun SectionDTO.toModel() = SectionModel(
    components = this.components.map { it.toModel() },
)