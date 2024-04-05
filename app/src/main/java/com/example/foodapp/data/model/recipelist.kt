package com.example.foodapp.data.model

data class recipelist (
    val count : Int,
    val next : String,
    val previous : String,
    val results : List<recipe>
)