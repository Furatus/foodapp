package com.example.foodapp


import LoadingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.recipelist.SearchBar
import com.example.foodapp.recipelist.SearchState
import com.example.foodapp.recipelist.ShowList
import com.example.foodapp.ui.theme.FoodappTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            FoodappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    LoadingScreen(navController = navController)

                    var recipeSearch by remember { mutableStateOf<String?>("") }

                    val searchState = remember { SearchState(onSearchSubmit = {}) }

                    LaunchedEffect(recipeSearch, searchState) {
                        searchState.onSearchSubmit = { query ->
                            recipeSearch = query
                        }
                    }
                    Column (modifier = Modifier.padding(5.dp)){
                        SearchBar(searchState = searchState)
                        Spacer(modifier = Modifier.padding(20.dp))
                        ShowList(recipeSearch)
                    }
                }
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