package com.tghc.recipebook.data.remote.dto_mapper

import com.tghc.recipebook.data.remote.dto.IngredientDto
import com.tghc.recipebook.domain.model.Ingredient

fun IngredientDto.toModel() = Ingredient(
    ingredient = ingredient,
    num = num,
    type = type,
)

fun Ingredient.toDto() = IngredientDto(
    ingredient = ingredient,
    num = num,
    type = type,
)

fun List<IngredientDto>.toModelList() = map { it.toModel() }

fun List<Ingredient>.toDtoList() = map { it.toDto() }