package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.data.repo.RecipeRepositoryImpl

class GetRecipeCountLocalUseCase(private val recipeRepositoryImpl: RecipeRepositoryImpl) {

    operator fun invoke() = recipeRepositoryImpl.getRecipeLocalCount()
}
