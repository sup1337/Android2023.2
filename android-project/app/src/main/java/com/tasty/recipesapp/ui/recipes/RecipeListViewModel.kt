package com.tasty.recipesapp.ui.recipes

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dtos.InstructionDTO
import java.io.IOException

class RecipeListViewModel: ViewModel() {
    fun fetchInstructionData() {
        val jsonString = """{
        "appliance": null,
        "end_time": 26500,
        "temperature": null,
        "id": 43381,
        "position": 1,
        "display_text": "In a blender or food processor, combine the yogurt, lime
        juice, jalapeno, cilantro, and jalapeno juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and
        pulse to blend until creamy.",
        "start_time": 7000
    }"""
        val gson = Gson()
        val instructionDTO = gson.fromJson(jsonString, InstructionDTO::class.java)
        Log.i("InstructionDTO", instructionDTO.toString())}
    fun fetchInstructionsData() {
        val jsonString = """
    [
        {
            "appliance": null,
            "end_time": 26500,
            "temperature": null,
            "id": 43381,
            "position": 1,
            "display_text": "In a blender or food processor, combine the yogurt, lime juice, jalapeno, cilantro, and jalapeno juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and pulse to blend until creamy.",
            "start_time": 7000
        },
        {
            // Add more instruction objects as needed
        }
    ]
    """
        val gson = Gson()
        val instructionDTOList = gson.fromJson(jsonString, object : TypeToken<List<InstructionDTO>>() {}.type)
        for (instructionDTO in instructionDTOList) {
            Log.i("InstructionDTO", instructionDTO.toString())
        }
    }

    fun readInstructionsFromFile(context: Context) {
        try {
            val inputStream = context.assets.open("instructions.json") // Fájlnévnek megfelelően változtasd meg
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val gson = Gson()
            val type = object : TypeToken<List<InstructionDTO>>() {}.type
            val instructionDTOList = gson.fromJson<List<InstructionDTO>>(jsonString, type)
            for (instructionDTO in instructionDTOList) {
                Log.i("InstructionDTO", instructionDTO.toString())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
