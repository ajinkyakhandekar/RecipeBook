package com.tghc.recipebook.data.local.entity_mapper

import com.tghc.recipebook.data.local.entity.RecipeEntity
import com.tghc.recipebook.domain.model.Ingredient
import com.tghc.recipebook.domain.model.Recipe

fun RecipeEntity.toModel() = Recipe(
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
    timestamp = timestamp,
    xtra_1 = xtra_1,
    xtra_2 = xtra_2,
    xtra_3 = xtra_3,
    xtra_4 = xtra_4,
    xtra_5 = xtra_5
)


fun Recipe.toEntity() = RecipeEntity(
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
    ingredient = ingredient.toEntityList(),
    procedure = procedure,
    imagePath = imagePath,
    imageId = imageId,
    tags = tags,
    timestamp = timestamp,
    xtra_1 = xtra_1,
    xtra_2 = xtra_2,
    xtra_3 = xtra_3,
    xtra_4 = xtra_4,
    xtra_5 = xtra_5,
)

fun List<RecipeEntity>.toModelList() = map { it.toModel() }

fun List<Recipe>.toEntityList() = map { it.toEntity() }
