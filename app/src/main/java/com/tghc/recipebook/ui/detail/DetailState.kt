package com.tghc.recipebook.ui.detail

import com.tghc.recipebook.domain.model.Recipe

data class DetailState(
    var isLoading: Boolean = false,
    var recipe: Recipe,
    var errorMessage: String = ""
)