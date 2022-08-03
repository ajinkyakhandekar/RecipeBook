package com.tghc.recipebook.data.remote.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class RecipeDto(
    @DocumentId
    var recipeId: String = "",
    var userId: String = "",
    var id: String = "",
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
    var ingredient: List<IngredientDto> = listOf(),
    var procedure: List<String> = listOf(),
    var imagePath: List<String> = listOf(),
    var imageId: List<String> = listOf(),
    var tags: List<String> = listOf(),
    @ServerTimestamp
    var timestamp: Date? = Date(),
    var xtra_1: String = "",
    var xtra_2: String = "",
    var xtra_3: String = "",
    var xtra_4: String = "",
    var xtra_5: String = "",
)

data class IngredientDto(
    var ingredient: String = "",
    var num: String = "",
    var type: String = ""
)
