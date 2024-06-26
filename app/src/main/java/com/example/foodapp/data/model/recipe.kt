package com.example.foodapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.net.URLEncoder


@Entity
data class recipe (
    @PrimaryKey
    val pk : Int,
    val title : String,
    val publisher : String,
    val featured_image : String,
    val rating : Int,
    val source_url : String,
    val description : String,
    val cooking_instructions : String?,
    val ingredients : List<String>,
    val date_added : String,
    val date_updated : String,
    val long_date_added : Long,
    val long_date_updated: Long
) {

    fun toJson(): String {
        val recipeJson =  Gson().toJson(this)
        return URLEncoder.encode(recipeJson, "UTF-8")
    }
}