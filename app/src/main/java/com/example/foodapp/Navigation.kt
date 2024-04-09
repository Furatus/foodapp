package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodapp.data.RecipeDao
import com.example.foodapp.data.model.recipe
import com.example.foodapp.recipedetails.RecipeDetailsScreen
import com.google.gson.Gson

@Composable
fun Navigation(recipeDao: RecipeDao) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RecipesScreens.RecipeListScreen.route) {
        composable(route = RecipesScreens.RecipeListScreen.route) {
            RecipeListScreen(recipeDao = recipeDao, navController = navController )
        }
        composable(
            route = "${RecipesScreens.RecipeDetailsScreen.route}/{recipeJson}",
            arguments = listOf(
                navArgument("recipeJson") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val recipeJson = backStackEntry.arguments?.getString("recipeJson") ?: ""
            val recipe = recipeJson.toRecipe()
            RecipeDetailsScreen(recipe = recipe)
        }
    }
}

fun String.toRecipe(): recipe {
    return Gson().fromJson(this, recipe::class.java)
}