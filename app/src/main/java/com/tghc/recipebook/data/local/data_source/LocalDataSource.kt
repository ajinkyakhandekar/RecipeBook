package com.tghc.recipebook.data.local.data_source

import com.tghc.recipebook.data.local.database.RecipeDao
import com.tghc.recipebook.data.local.entity.RecipeEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {
    suspend fun insertRecipeList(recipeEntityList: List<RecipeEntity>) =
        recipeDao.insertRecipeList(recipeEntityList)

    fun getRecipeListLocal() = recipeDao.getRecipeListLocal()

    fun getRecipeLocal(recipeId : String) = recipeDao.getRecipeLocal(recipeId)

    fun getRecipeCount() = recipeDao.getRecipeCount()

    fun deleteRecipeLocal(recipeId : String) = recipeDao.deleteRecipeLocal(recipeId)

}