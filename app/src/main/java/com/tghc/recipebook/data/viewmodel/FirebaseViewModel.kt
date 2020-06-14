package com.tghc.recipebook.data.viewmodel

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tghc.recipebook.data.model.Item
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.response.BaseResponse
import com.tghc.recipebook.data.service.FirebaseService
import kotlinx.coroutines.Dispatchers

class FirebaseViewModel : ViewModel() {

    fun getItemList(page: Int) = liveData<BaseResponse<MutableList<Item>>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.getItemListService(page))
    }

    fun getRecipe(recipeId: String) = liveData<BaseResponse<Recipe?>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.getRecipeService(recipeId))
    }

    fun postImage(path:String) =  liveData<BaseResponse<String>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.postImageService(path))
    }

    fun postRecipe(recipe: Recipe) = liveData<BaseResponse<String>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.postRecipeService(recipe))
    }

    fun postItem(item: Item) = liveData<BaseResponse<Boolean>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.postItemService(item))
    }

    fun deleteRecipe(itemId: String, recipeId: String) = liveData<BaseResponse<Boolean>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.deleteRecipeService(itemId, recipeId))
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