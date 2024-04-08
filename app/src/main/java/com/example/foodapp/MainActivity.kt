package com.example.foodapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodapp.data.RecipeDB
import com.example.foodapp.data.RecipeDao
import com.example.foodapp.recipelist.SearchBar
import com.example.foodapp.recipelist.SearchState
import com.example.foodapp.recipelist.ShowList
import com.example.foodapp.recipelist.ButtonfilterRow
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
                    var recipeSearch by remember { mutableStateOf<String?>("") }

                    val searchState = remember { SearchState(onSearchSubmit = {}) }

                    LaunchedEffect(recipeSearch, searchState) {
                        searchState.onSearchSubmit = { query ->
                            recipeSearch = query
                        }
                    }
                    Column (modifier = Modifier.padding(5.dp)){
                        SearchBar(searchState = searchState)
                        Spacer(modifier = Modifier.padding(2.dp))
                        ButtonfilterRow(searchState = searchState)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Divider(thickness = 1.dp)
                        ShowList(recipeSearch, recipeDao)
                    }
                }
            }
        }
    }
}