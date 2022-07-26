package com.tghc.recipebook.data.repo

import com.tghc.recipebook.domain.model.Recipe

interface RecipeRepository {
    fun getRecipeList() : List<Recipe>
    fun getRecipe() : Recipe
    fun insertRecipe(recipe: Recipe)
}