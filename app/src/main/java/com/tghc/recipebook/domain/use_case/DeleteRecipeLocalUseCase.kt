package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.data.repo.RecipeRepositoryImpl

class DeleteRecipeLocalUseCase (private val recipeRepositoryImpl: RecipeRepositoryImpl) {

    operator fun invoke(recipeId : String) = recipeRepositoryImpl.deleteRecipeLocal(recipeId)
}