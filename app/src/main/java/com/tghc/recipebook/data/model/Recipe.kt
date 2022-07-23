package com.tghc.recipebook.data.model

import androidx.annotation.Keep
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.*

@Keep
data class Recipe(
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
    var ingredient: MutableList<Ingredient> = ArrayList(),
    var procedure: MutableList<String> = ArrayList(),
    var imagePath: MutableList<String> = ArrayList(),
    var imageId: MutableList<String> = ArrayList(),
    var tags: MutableList<String> = ArrayList(),
    @ServerTimestamp
    var timestamp: Date = Date(),
    var xtra_1 : String = "",
    var xtra_2 : String = "",
    var xtra_3 : String = "",
    var xtra_4 : String = "",
    var xtra_5 : String = "",
) : Serializable

@Keep
data class Ingredient(
    var ingredient: String = "",
    var num: String = "",
    var type: String = ""
) : Serializable
