package com.example.foodapp.recipelist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodapp.data.RecipeDao
import com.example.foodapp.data.fetchRecipesJsonData
import com.example.foodapp.data.model.recipelist
import com.example.foodapp.recipelist.recipecard.RecipeCard
import kotlinx.coroutines.delay


@Composable
fun ShowList(search : String? = "", recipeDao: RecipeDao) {
    var recipelist by remember { mutableStateOf<recipelist?>(null) }
    var currentPage by remember { mutableIntStateOf(1) }
    val listState = rememberLazyListState()
    var noResultDisplayed by remember { mutableStateOf(false) }


    LaunchedEffect(search) {
        noResultDisplayed = false
        currentPage = 1
        recipelist = recipelist?.copy(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )

        delay(5000)
        if (recipelist?.results.isNullOrEmpty() && !noResultDisplayed) {
            noResultDisplayed = true
        }
    }
    LaunchedEffect(search, currentPage) {

            val newRecipes = fetchRecipesJsonData(query = search, page = currentPage, recipeDao = recipeDao)
            if (newRecipes.results.isNotEmpty()) {
                recipelist = if (recipelist == null) {
                    newRecipes
                } else {
                    recipelist!!.copy(results = recipelist!!.results + newRecipes.results)
                }
            }
    }

    if(recipelist?.results.isNullOrEmpty() && !noResultDisplayed) {
        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    }
    else if(noResultDisplayed) {
        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoResult()
        }
    }
    else {
        recipelist?.let { recipeList ->
            LazyColumn(state = listState) {
                itemsIndexed(recipeList.results) { _, recipe ->
                    RecipeCard(ImageUrl = recipe.featured_image, RecipeName = recipe.title)
                    Log.d("recipe_title", recipe.title)
                }
            }
        }
    }

        fun LazyListState.isScrolledToBottom(): Boolean {
            val itemCount = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            return lastVisibleItemIndex >= itemCount - 3
        }

        val endOfListReached by remember {
            derivedStateOf {
                listState.isScrolledToBottom()
            }
        }

        LaunchedEffect(endOfListReached) {
            currentPage++
        }
}

@Composable
fun NoResult() {
    Text(text = "No Result found")
}