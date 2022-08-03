package com.tghc.recipebook.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class DeleteRecipeUseCase(
    private val deleteRecipeLocalUseCase: DeleteRecipeLocalUseCase,
    private val deleteRecipeRemoteUseCase: DeleteRecipeRemoteUseCase
) {
    operator fun invoke(recipeId: String) = channelFlow {

        launch(Dispatchers.IO) {
            deleteRecipeRemoteUseCase(recipeId).collect { status ->
                if (status) {
                    deleteRecipeLocalUseCase(recipeId).collect {
                        send(it == 1)
                    }
                }
            }
        }
    }
}