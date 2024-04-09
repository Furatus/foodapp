package com.example.foodapp.recipedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.foodapp.data.model.recipe

@Composable
fun RecipeDetailsScreen(recipe: recipe) {
    val imageloader = ImageLoader(context = LocalContext.current)
    AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(recipe.featured_image).crossfade(true).build(), contentDescription = "description_test", imageLoader = imageloader, contentScale = ContentScale.Crop, modifier = Modifier
        .height(300.dp)
        .fillMaxWidth())
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = recipe.title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = recipe.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = recipe.date_added,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Ingrédients:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = "- $ingredient",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
}