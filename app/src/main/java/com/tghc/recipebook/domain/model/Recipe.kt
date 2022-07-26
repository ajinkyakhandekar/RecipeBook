package com.tghc.recipebook.domain.model

import java.util.*

data class Recipe(
    var recipeId: Long = 0L,
    var userId: String = "",
    var title: String = "",
    var type: String = "",
    var chef: String = "",
    var cuisine: String = "",
    var subCuisine: String = "",
    var cTime: String = "",
    var pTime: String = "",
    var description: String = "",
    var servings: String = "",
    var notes: String = "",
    var ingredient: MutableList<Ingredient> = mutableListOf(),
    var procedure: MutableList<String> = mutableListOf(),
    var imagePath: MutableList<String> = mutableListOf(),
    var imageId: MutableList<String> = mutableListOf(),
    var tags: MutableList<String> = mutableListOf(),
    var timestamp: Long = 0L,
    var xtra_1: String = "",
    var xtra_2: String = "",
    var xtra_3: String = "",
    var xtra_4: String = "",
    var xtra_5: String = "",
)

data class Ingredient(
    var ingredient: String = "",
    var num: String = "",
    var type: String = ""
)
