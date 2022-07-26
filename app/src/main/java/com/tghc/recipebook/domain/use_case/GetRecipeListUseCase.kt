package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.extention.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class GetRecipeListUseCase(
    private val isUpdateRequiredUseCase: IsUpdateRequiredUseCase,
    private val getRecipeListLocalUseCase: GetRecipeListLocalUseCase,
    private val getRecipeListRemoteUseCase: GetRecipeListRemoteUseCase
) {
    operator fun invoke() = channelFlow {

        isUpdateRequiredUseCase().collect { isUpdateRequired ->
            isUpdateRequired.log("is Update Required")

            launch(Dispatchers.IO) {
                if (isUpdateRequired) getRecipeListRemoteUseCase().collect {
                    send(it)
                }
                else getRecipeListLocalUseCase().collect {
                    send(it)
                }
            }
        }
    }
}