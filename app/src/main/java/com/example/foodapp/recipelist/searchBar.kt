package com.example.foodapp.recipelist

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text


import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

class SearchState(var onSearchSubmit: (String) -> Unit)

@Composable
fun SearchBar(searchState: SearchState) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { searchState.onSearchSubmit(searchText) }
        ) {
            Text(text = "Rechercher")
        }
    }
}