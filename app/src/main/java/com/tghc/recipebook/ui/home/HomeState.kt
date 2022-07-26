package com.tghc.recipebook.ui.home

import com.tghc.recipebook.domain.model.Recipe

data class HomeState(
    var isLoading: Boolean = false,
    var recipeList: List<Recipe> = emptyList(),
    var errorMessage: String = ""
)