package com.example.foodapp.recipelist.recipecard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.foodapp.RecipesScreens
import com.example.foodapp.data.model.recipe


@Composable
fun RecipeCard(ImageUrl : String, RecipeName : String, navController: NavController, recipe: recipe) {
OutlinedCard (modifier = Modifier
    .fillMaxWidth()
    .height(250.dp)
    .requiredHeight(250.dp)
    .clickable(onClick = {val recipeJson = recipe.toJson()
         navController.navigate(route = "${RecipesScreens.RecipeDetailsScreen.route}/${recipeJson}")})
    .padding(16.dp)){
    Column (modifier = Modifier.padding(0.dp)) {
        val imageloader = ImageLoader(context = LocalContext.current)
        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(ImageUrl).crossfade(true).build(), contentDescription = "description_test", imageLoader = imageloader, contentScale = ContentScale.Crop, modifier = Modifier
            .height(150.dp)
            .fillMaxWidth())
        Spacer(modifier = Modifier.padding(4.dp))
            Text(text = RecipeName, modifier =  Modifier.padding(horizontal = 3.dp))
    }
}
}