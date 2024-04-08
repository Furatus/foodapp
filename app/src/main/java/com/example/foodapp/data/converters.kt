package com.example.foodapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientsTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredients(ingredients : List<String>) : String = gson.toJson(ingredients)

    @TypeConverter
    fun toIngredients(ingredientsJson : String) : List<String> = gson.fromJson(ingredientsJson, object : TypeToken<List<String>>() {}.type)

}