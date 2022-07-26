package com.tghc.recipebook.data.remote.dto_mapper

import com.tghc.recipebook.data.remote.dto.RecipeDto
import com.tghc.recipebook.domain.model.Ingredient
import com.tghc.recipebook.domain.model.Recipe

fun RecipeDto.toModel() = Recipe(
    recipeId = recipeId,
    userId = userId,
    title = title,
    type = type,
    chef = chef,
    cuisine = cuisine,
    subCuisine = subCuisine,
    cTime = cTime,
    pTime = pTime,
    description = description,
    servings = servings,
    notes = notes,
    ingredient = ingredient.toModelList() as MutableList<Ingredient>,
    procedure = procedure as MutableList<String>,
    imagePath = imagePath as MutableList<String>,
    imageId = imageId as MutableList<String>,
    tags = tags as MutableList<String>,
    timestamp = timestamp?.time ?: 0,
    xtra_1 = xtra_1,
    xtra_2 = xtra_2,
    xtra_3 = xtra_3,
    xtra_4 = xtra_4,
    xtra_5 = xtra_5
)


fun Recipe.toDto() = RecipeDto(
    recipeId = recipeId,
    userId = userId,
    title = title,
    type = type,
    chef = chef,
    cuisine = cuisine,
    subCuisine = subCuisine,
    cTime = cTime,
    pTime = pTime,
    description = description,
    servings = servings,
    notes = notes,
    ingredient = ingredient.toDtoList(),
    procedure = procedure,
    imagePath = imagePath,
    imageId = imageId,
    tags = tags,
    timestamp = null,
    xtra_1 = xtra_1,
    xtra_2 = xtra_2,
    xtra_3 = xtra_3,
    xtra_4 = xtra_4,
    xtra_5 = xtra_5,
)

fun List<RecipeDto>.toModelList() = map { it.toModel() }

fun List<Recipe>.toDtoList() = map { it.toDto() }
