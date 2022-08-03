package com.tghc.recipebook.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tghc.recipebook.common.ResponseStatus
import com.tghc.recipebook.domain.use_case.GetRecipeUseCase
import com.tghc.recipebook.extention.ifNull
import com.tghc.recipebook.extention.log
import com.tghc.recipebook.ui.detail.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    var recipeId = ""

    init {
        getRecipe()
    }

    private fun getRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeUseCase(recipeId).onEach { responseStatus ->
                when (responseStatus) {
                    is ResponseStatus.Loading -> {
                        "is Loading Detail".log()
                        _uiState.value = DetailState(
                            true,
                            null,
                        )
                    }
                    is ResponseStatus.Success -> {
                        "success - detail".log()
                        _uiState.value = DetailState(
                            false,
                            responseStatus.data,
                        )
                    }
                    is ResponseStatus.Error -> {
                        _uiState.value = DetailState(
                            false,
                            null,
                            responseStatus.message.ifNull("MSG_ERROR")
                        )
                    }
                }
            }.collect()
        }
    }

    fun deleteRecipe(recipeId : String){
        de
    }
}
