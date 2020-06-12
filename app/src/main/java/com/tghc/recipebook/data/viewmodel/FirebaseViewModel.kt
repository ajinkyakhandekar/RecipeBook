package com.tghc.recipebook.data.viewmodel

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tghc.recipebook.data.modelRequest.Recipe
import com.tghc.recipebook.data.response.BaseResponse
import com.tghc.recipebook.data.service.FirebaseService
import kotlinx.coroutines.Dispatchers

class FirebaseViewModel : ViewModel(){

    fun getRecipe(username: String) = liveData<BaseResponse<Boolean>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.getRecipeListService(username))
    }

    fun postRecipe(recipe: Recipe) = liveData<BaseResponse<Void>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.postRecipeService(recipe))
    }

    private suspend fun <T> onResponse(liveDataScope: LiveDataScope<BaseResponse<T>>, response: T) {
        liveDataScope.emit(BaseResponse.loading())
        try {
            liveDataScope.emit(BaseResponse.success(response))
        } catch (e: Exception) {
            liveDataScope.emit(BaseResponse.error(null))
            e.printStackTrace()
        }
    }
}