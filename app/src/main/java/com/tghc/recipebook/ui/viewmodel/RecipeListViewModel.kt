package com.tghc.recipebook.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tghc.recipebook.common.ResponseStatus
import com.tghc.recipebook.domain.model.Recipe
import com.tghc.recipebook.domain.use_case.GetRecipeListUseCase
import com.tghc.recipebook.extention.ifNull
import com.tghc.recipebook.extention.isNull
import com.tghc.recipebook.extention.log
import com.tghc.recipebook.ui.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        getRecipeList()
    }

    fun getRecipeList() {
        getRecipeListUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    "is Loading".log()
                    _uiState.value = HomeState(
                        true,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Success -> {
                    "success".log()
                    responseStatus.data?.size.log("final list size")
                    _uiState.value = HomeState(
                        false,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Error -> {
                    _uiState.value = HomeState(
                        false,
                        responseStatus.data.orEmpty(),
                        responseStatus.message.ifNull("MSG_ERROR")
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}


/*fun getRecipeList(db: FirebaseFirestore, page: Int) =
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
}*/
