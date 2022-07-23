package com.tghc.recipebook.data.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.firestore.FirebaseFirestore
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.response.BaseResponse
import com.tghc.recipebook.data.service.FirebaseService
import kotlinx.coroutines.Dispatchers

class FirebaseViewModel : ViewModel() {

    fun getRecipeList(db: FirebaseFirestore, page: Int) =
        liveData<BaseResponse<MutableList<Recipe>>>(Dispatchers.IO) {
            onResponse(this, FirebaseService.getRecipeListService(db, page))
        }

    fun getRecipe(db: FirebaseFirestore, recipeId: String) =
        liveData<BaseResponse<Recipe?>>(Dispatchers.IO) {
            onResponse(this, FirebaseService.getRecipeService(db, recipeId))
        }

    fun postImage(uri: Uri) = liveData<BaseResponse<String>>(Dispatchers.IO) {
        onResponse(this, FirebaseService.postImageService(uri))
    }

    fun postRecipe(db: FirebaseFirestore, recipe: Recipe) =
        liveData<BaseResponse<String>>(Dispatchers.IO) {
            onResponse(this, FirebaseService.postRecipeService(db, recipe))
        }

    fun postRecipeList(db: FirebaseFirestore, recipeList: MutableList<Recipe>) =
        liveData<BaseResponse<String>>(Dispatchers.IO) {
            onResponse(this, FirebaseService.postRecipeListService(db, recipeList))
        }

    fun deleteRecipe(db: FirebaseFirestore, recipeId: String) =
        liveData<BaseResponse<Boolean>>(Dispatchers.IO) {
            onResponse(this, FirebaseService.deleteRecipeService(db, recipeId))
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