package com.example.foodapp.recipelist.recipecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun RecipeCard(ImageUrl : String, RecipeName : String) {
OutlinedCard (modifier = Modifier
    .fillMaxWidth()
    .height(350.dp)
    .requiredHeight(350.dp)
    .padding(16.dp)){
    Column (modifier = Modifier.padding(16.dp)) {
        val imageloader = ImageLoader(context = LocalContext.current)
        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(ImageUrl).crossfade(true).build(), contentDescription = "description_test", imageLoader = imageloader, modifier = Modifier
            .height(150.dp)
            .fillMaxWidth())
        Spacer(modifier = Modifier.padding(16.dp))

        Row {
            Text(text = RecipeName/*, Modifier.size(50.dp).fillMaxWidth()*/)
            Text(text = "test");
        }
    }
}
}