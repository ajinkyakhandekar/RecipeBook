package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.data.repo.RecipeRepositoryImpl

class DeleteRecipeRemoteUseCase (private val recipeRepositoryImpl: RecipeRepositoryImpl) {

    operator fun invoke(recipeId : String) = recipeRepositoryImpl.deleteRecipeRemote(recipeId)
}