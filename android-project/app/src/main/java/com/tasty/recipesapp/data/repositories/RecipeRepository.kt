package com.tasty.recipesapp.data.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.utils.*
import org.json.JSONObject
import java.io.IOException

class RecipeRepository: IGenericRepository<RecipeDTO, RecipeModel> {
    override fun RecipeDTO.toModel(): RecipeModel {
        return RecipeModel(
            name = this.name,
            description = this.description,
            thumbnailUrl = this.thumbnailUrl,
            numServings = this.numServings,
            cookTimeMinutes = this.cookTimeMinutes,
            nutrition = this.nutrition.toModel(),
            instructions = this.instructions.map { it.toModel() },
            sections = this.sections.map { it.toModel() },
            tags = this.tags.map { it.toModel() },
            topics = this.topics.map { it.toModel() },
            credits = this.credits.map { it.toModel() },
            userRatings = this.userRatings.toModel(),
        )
    }

    override fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
        return this.map { it.toModel() }
    }

    override fun getAll(context: Context): List<RecipeModel> {
        return readAll(context).toModelList()
    }

    override fun readAll(context: Context): List<RecipeDTO> {
        val gson = Gson()
        var recipeList = listOf<RecipeDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("recipes_all.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            //If there is an extra label
            val jsonObject = JSONObject(jsonString)
            val recipeArray = jsonObject.getJSONArray("results")

            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            //if it is simple
            //val instructionList = gson.fromJson<List<InstructionDTO>>(jsonString, type)
            // if with label
            recipeList = gson.fromJson(recipeArray.toString(), type)


            Log.i("RecipeGSON", recipeList.toString())
            //instructions.value = instructionList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }
}