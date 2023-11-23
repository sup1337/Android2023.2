package com.tasty.recipesapp.data.repositories


import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.InstructionTime
import com.tasty.recipesapp.utils.Mapping.toModelList
import org.json.JSONObject
import java.io.IOException

class InstructionRepository: IGenericRepository<InstructionDTO, InstructionModel> {
    override fun InstructionDTO.toModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    override fun List<InstructionDTO>.toModelList(): List<InstructionModel> {
        return this.map { it.toModel() }
    }

    // later, context should be removed
    override fun getAll(context: Context): List<InstructionModel> {
        return readAll(context).toModelList()
    }

    // In the future this should be deleted and data should be fetched from a public API
    override fun readAll(context : Context): List<InstructionDTO> {
        val gson = Gson()
        var instructionList = listOf<InstructionDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("instructions.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            //If there is an extra label
            val jsonObject = JSONObject(jsonString)
            val instructionsArray = jsonObject.getJSONArray("instructions")

            val type = object : TypeToken<List<InstructionDTO>>() {}.type

            instructionList = gson.fromJson(instructionsArray.toString(), type)


            Log.i("GSON2", instructionList.toString())
            //instructions.value = instructionList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return instructionList
    }
}


