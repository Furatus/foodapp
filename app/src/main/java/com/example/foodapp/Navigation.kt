package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.data.RecipeDao

@Composable
fun Navigation(recipeDao: RecipeDao) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RecipesScreens.RecipeListScreen.route) {
        composable(route = RecipesScreens.RecipeListScreen.route) {
            RecipeListScreen(recipeDao = recipeDao, navController = navController )
        }
        /*composable(route = RecipesScreens.RecipeDetailsScreen.route + "/{recipe}", arguments = listOf(
            navArgument("recipe") {
                type = NavType.ParcelableType(recipe::class.java)
                defaultValue = null;
                nullable = false
            }
            )
        ) {backStackEntry ->
            val recipe = backStackEntry.arguments?.getParcelable<recipe>("recipe")
            RecipeDetailsScreen(recipe!!)
        }*/
    }
}