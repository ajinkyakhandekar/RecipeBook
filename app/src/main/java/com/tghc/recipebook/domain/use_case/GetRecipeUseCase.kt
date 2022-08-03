package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.data.repo.RecipeRepositoryImpl

class GetRecipeUseCase(private val recipeRepositoryImpl: RecipeRepositoryImpl) {

    operator fun invoke(recipeId : String) = recipeRepositoryImpl.getRecipeLocal(recipeId)
}
