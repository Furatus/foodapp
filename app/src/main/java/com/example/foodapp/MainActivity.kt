package com.example.foodapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.data.fetchRecipesJsonData
import com.example.foodapp.data.model.recipelist
import com.example.foodapp.recipelist.recipecard.RecipeCard
import com.example.foodapp.ui.theme.FoodappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowList()
                }
            }
        }
    }
}

/*@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    //RecipeCard(ImageUrl = "https://cataas.com/cat", RecipeName = "test")

    var recipelist : recipelist
    coroutineScope.launch { recipelist = fetchRecipesJsonData(query = "") }

    LazyColumn {
        for (result : recipe in recipelist.results) {
            RecipeCard(ImageUrl = result.featured_image, RecipeName = result.title)
        }
    }

}*/

@Composable
fun ShowList(search : String = "") {

    var recipelist by remember { mutableStateOf<recipelist?>(null) }

    LaunchedEffect(Unit) {
        recipelist = fetchRecipesJsonData(query = search)
    }

    recipelist?.let { recipeList ->
        LazyColumn {
            itemsIndexed(recipeList.results) { _ , recipe ->
                RecipeCard(ImageUrl = recipe.featured_image, RecipeName = recipe.title)
                Log.d("recipe_title", recipe.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodappTheme {
        ShowList()
    }
}