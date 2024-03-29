package com.tasty.recipesapp.data.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.entitys.RecipeDao
import com.tasty.recipesapp.data.entitys.RecipeEntity
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.data.service.RecipeApiClient
import com.tasty.recipesapp.ui.recipes.Order
import com.tasty.recipesapp.ui.recipes.Sort
import com.tasty.recipesapp.utils.*
import org.json.JSONObject
import java.io.IOException
class RecipeRepository(private val recipeDao: RecipeDao): IGenericRepository<RecipeDTO, RecipeModel> {
    private val apiClient = RecipeApiClient()


    override fun RecipeDTO.toModel(): RecipeModel {
        return RecipeModel(
            id = this.id,
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

    suspend fun insertRecipe(recipe: RecipeEntity) {
        return recipeDao.insertRecipe(recipe)
    }

    suspend fun getFromDb(): List<RecipeModel> {
        val data = recipeDao.getAllRecipes()
        return data.map { entityToModel(it) }
    }

    suspend fun deleteRecipe(id: Long) {
        return recipeDao.deleteRecipeById(id)
    }

    private fun entityToModel(entity: RecipeEntity): RecipeModel {
        val recipe = Gson().fromJson(entity.json, RecipeModel::class.java)
        recipe.id = entity.internalId.toInt()
        return recipe
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
    suspend fun getRecipesFromApi(from: String, size: String, tags: String? = null, search: String? = null, sort: Sort? = null, order: Order? = null): List<RecipeModel> {
        var data = apiClient.getRecipes(from, size, tags, search).results.toModelList()

        if (sort != null && order != null) {

            if (sort == Sort.NAME) {
                data = data.sortedBy { it -> it.name }
            }

            if (sort == Sort.RATING) {
                data = data.sortedBy { it -> it.userRatings?.score }
            }

            if (order == Order.DESC) {
                data = data.reversed()
            }
        }

        return data
    }


}