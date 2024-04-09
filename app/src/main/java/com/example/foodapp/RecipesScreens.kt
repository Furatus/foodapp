package com.example.foodapp

sealed class RecipesScreens(val route: String) {
    object RecipeListScreen : RecipesScreens("list_screen")
    object RecipeDetailsScreen : RecipesScreens("detail_screen")

}