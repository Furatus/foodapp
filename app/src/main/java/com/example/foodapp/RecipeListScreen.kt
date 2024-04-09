package com.example.foodapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodapp.data.RecipeDao
import com.example.foodapp.recipelist.ButtonfilterRow
import com.example.foodapp.recipelist.SearchBar
import com.example.foodapp.recipelist.SearchState
import com.example.foodapp.recipelist.ShowList

@Composable
fun RecipeListScreen (recipeDao: RecipeDao, navController : NavController) {
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
        ShowList(recipeSearch, recipeDao, navController)
    }
}