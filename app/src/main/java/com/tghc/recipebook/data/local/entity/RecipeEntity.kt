package com.tghc.recipebook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tghc.recipebook.common.RECIPE_TABLE
import com.tghc.recipebook.domain.model.Recipe
import java.util.*

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
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
    var ingredient: List<IngredientEntity> = listOf(),
    var procedure: List<String> = listOf(),
    var imagePath: List<String> = listOf(),
    var imageId: List<String> = listOf(),
    var tags: List<String> = listOf(),
    var timestamp: Long = 0L,
    var xtra_1: String = "",
    var xtra_2: String = "",
    var xtra_3: String = "",
    var xtra_4: String = "",
    var xtra_5: String = ""
)

data class IngredientEntity(
    var ingredient: String = "",
    var num: String = "",
    var type: String = ""
)