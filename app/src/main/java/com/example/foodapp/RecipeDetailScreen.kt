package com.example.foodapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.data.model.Recipe
import com.example.foodapp.data.model.recipelist
import com.example.foodapp.ui.theme.FoodappTheme

@Composable
fun RecipeDetailScreen(recipe: recipelist) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = recipe.title, style = MaterialTheme.typography.headlineLarge)
    }
}

@Preview
@Composable
fun RecipeDetailScreenPreview() {
    FoodappTheme {
        val sampleRecipe = Recipe()
        RecipeDetailScreen(recipe = sampleRecipe)
    }
}
