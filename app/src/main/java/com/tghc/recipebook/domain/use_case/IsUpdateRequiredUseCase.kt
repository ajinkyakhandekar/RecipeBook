package com.tghc.recipebook.domain.use_case

import com.tghc.recipebook.extention.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class IsUpdateRequiredUseCase(
    private val getRecipeCountLocalUseCase: GetRecipeCountLocalUseCase,
    private val getRecipeCountRemoteUseCase: GetRecipeCountRemoteUseCase
) {

    operator fun invoke() = channelFlow  {

        "launched flow".log()

        launch (Dispatchers.IO){
            val localCountFlow = getRecipeCountLocalUseCase()
            val remoteCountFlow = getRecipeCountRemoteUseCase()

            localCountFlow.zip(remoteCountFlow) { localCount, remoteCount ->
                localCount.log("local count")
                remoteCount.log("remote count")
                (remoteCount > localCount)
            }.collect{
                send(it)
            }
        }
    }
}