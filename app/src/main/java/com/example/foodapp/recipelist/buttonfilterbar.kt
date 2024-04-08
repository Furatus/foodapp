package com.example.foodapp.recipelist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ButtonfilterRow(searchState: SearchState) {
    val filters = arrayOf("Beef", "Pork", "Veggie", "Pizza", "Cheese", "Chicken")

    LazyRow {
        itemsIndexed(filters) {_ ,  filter ->
            Buttonfilter(searchState = searchState, text = filter)
        }
    }
}


@Composable
fun Buttonfilter (searchState: SearchState, text : String) {
    Button(onClick = {searchState.onSearchSubmit(text)}, modifier = Modifier.padding(1.dp)) {
        Text(text = text)
    }
}