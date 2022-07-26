package com.tghc.recipebook.data.local.entity_mapper

import com.tghc.recipebook.data.local.entity.IngredientEntity
import com.tghc.recipebook.domain.model.Ingredient

fun IngredientEntity.toModel() = Ingredient(
    ingredient = ingredient,
    num = num,
    type = type,
)

fun Ingredient.toEntity() = IngredientEntity(
    ingredient = ingredient,
    num = num,
    type = type,
)

fun List<IngredientEntity>.toModelList() = map { it.toModel() }

fun List<Ingredient>.toEntityList() = map { it.toEntity() }
