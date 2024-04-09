package com.example.foodapp.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.data.model.recipe
import com.example.foodapp.data.model.recipelist
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import java.net.URLEncoder

suspend fun fetchRecipesJsonData(query : String? = "", page: Int = 1, recipeDao: RecipeDao) : recipelist {
    try {
        val client = HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("ktor.io")
                }
            }
            defaultRequest {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
                    )
                }
            }
        }

        val response = client.get {
            url {
                protocol = URLProtocol.HTTPS
                this.host = "food2fork.ca"
                encodedPath =
                    "/api/recipe/search/?query=${URLEncoder.encode(query, "UTF-8")}&page=${page}"
            }
        }

        //Log.d("http-response", response.bodyAsText())

        if (response.status.value == 200) {
            val gson = Gson()
            val result = gson.fromJson(response.bodyAsText(), recipelist::class.java)
            //Log.d("data-json-deserialized", result.results.toString() )
            result.results.forEach { recipe ->
                recipeDao.insert(recipe)
                //Log.d("Room", "Inserted recipe ${recipe.title}")
            }

            return result
        }
        return recipelist(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
    } catch (e : Exception) {
        try {
            val recipes = recipeDao.getAllRecipes( "$query", (page-1) * 30)


            return recipelist( results = recipes , count = 0 , next = null , previous = null)
        }
        catch (_: Exception) {

            return recipelist(
                count = 0,
                next = null,
                previous = null,
                results = emptyList()
            )
        }

    }

}

@Database(entities = [recipe::class], version = 1, exportSchema = false)
@TypeConverters(IngredientsTypeConverter::class)
abstract class RecipeDB : RoomDatabase() {
    abstract fun RecipeDao(): RecipeDao

    companion object {
        @Volatile
        private var Instance: RecipeDB? = null

        fun getDatabase(context: Context): RecipeDB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RecipeDB::class.java, "recipe-database").build()
                    .also { Instance = it }
            }
        }
    }
}