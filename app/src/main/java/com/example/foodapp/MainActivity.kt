package com.example.foodapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.foodapp.data.RecipeDB
import com.example.foodapp.data.RecipeDao
import com.example.foodapp.ui.theme.FoodappTheme

class MainActivity : ComponentActivity() {
    private lateinit var recipeDao: RecipeDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val recipeDB = RecipeDB.getDatabase(this)
                    recipeDao = recipeDB.RecipeDao()
                    
                    Navigation(recipeDao = recipeDao)
                }
            }
        }
    }
}