package com.example.foodapp.recipelist

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import com.example.foodapp.data.fetchRecipesJsonData
import com.example.foodapp.data.model.recipelist
import com.example.foodapp.recipelist.recipecard.RecipeCard


@Composable
fun ShowList(search : String? = "") {
    var recipelist by remember { mutableStateOf<recipelist?>(null) }
    var currentPage by remember { mutableIntStateOf(1) }
    val listState = rememberLazyListState()

    LaunchedEffect(search) {
        currentPage = 1
        recipelist = recipelist?.copy(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
    }
    LaunchedEffect(search, currentPage) {

            val newRecipes = fetchRecipesJsonData(query = search, page = currentPage)
            if (newRecipes.results.isNotEmpty()) {
                recipelist = if (recipelist == null) {
                    newRecipes
                } else {
                    recipelist!!.copy(results = recipelist!!.results + newRecipes.results)
                }
            }
    }

    if(recipelist?.results.isNullOrEmpty()) {
        NoResult()
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