package com.example.foodapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.model.recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: recipe)

    @Query("SELECT * FROM recipe WHERE title LIKE '%' || :likeQuery || '%' ORDER BY pk ASC LIMIT 30 OFFSET :currentOffset")
    suspend fun getAllRecipes(likeQuery : String, currentOffset : Int) : List<recipe>
}