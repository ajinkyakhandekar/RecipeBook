package com.tghc.recipebook.data.repo

import com.tghc.recipebook.common.ResponseStatus
import com.tghc.recipebook.data.local.data_source.LocalDataSource
import com.tghc.recipebook.data.local.entity_mapper.toEntityList
import com.tghc.recipebook.data.local.entity_mapper.toModel
import com.tghc.recipebook.data.local.entity_mapper.toModelList
import com.tghc.recipebook.data.remote.data_source.RemoteDataSource
import com.tghc.recipebook.data.remote.dto_mapper.toModelList
import com.tghc.recipebook.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) //: RecipeRepository
{
    fun getRecipeListLocal() = flow {
        emit(ResponseStatus.Loading())
        val localRecipeList = localDataSource.getRecipeListLocal().toModelList()
        emit(ResponseStatus.Success(localRecipeList))
    }

    fun getRecipeListRemote() = flow {
        emit(ResponseStatus.Loading())
        val recipeList = remoteDataSource.getRecipeList().toModelList()
        localDataSource.insertRecipeList(recipeList.toEntityList())

        val localRecipeList = localDataSource.getRecipeListLocal().toModelList()
        emit(ResponseStatus.Success(localRecipeList))
    }

    fun getRecipeLocal(recipeId: String) = flow {
        emit(ResponseStatus.Loading())
        val localRecipe = localDataSource.getRecipeLocal(recipeId).toModel()
        emit(ResponseStatus.Success(localRecipe))
    }

    fun getRecipeLocalCount() = flow {
        println("launched local flow")
        emit(localDataSource.getRecipeCount())
    }

    fun getRecipeRemoteCount() = flow {
        emit(remoteDataSource.getRecipeCount())
    }

    fun deleteRecipeLocal(recipeId: String) = flow {
        emit(localDataSource.deleteRecipeLocal(recipeId))
    }

    fun deleteRecipeRemote(recipeId: String) = flow {
        emit(remoteDataSource.deleteRecipeRemote(recipeId))
    }

    /*override fun getRecipeList(): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun getRecipe(): Recipe {
        TODO("Not yet implemented")
    }

    override fun insertRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }*/

}
