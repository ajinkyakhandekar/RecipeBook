package com.tghc.recipebook.data.local.data_source

import com.tghc.recipebook.data.local.database.RecipeDao
import com.tghc.recipebook.data.local.entity.RecipeEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {

    /*suspend fun getRecipeList() = recipeDao.getRecipeList()

    suspend fun insertRecipeList(recipeEntityList: List<RecipeEntity>) =
        recipeDao.insertRecipeList(recipeEntityList)

    suspend fun insertRecipe(recipeEntity: RecipeEntity) =
        recipeDao.insertRecipe(recipeEntity)*/

    suspend fun insertRecipeList(recipeEntityList: List<RecipeEntity>) =
        recipeDao.insertRecipeList(recipeEntityList)

    fun getRecipeListLocal() = recipeDao.getRecipeListLocal()

    fun getRecipeCount() = recipeDao.getRecipeCount()

    /*suspend fun insertSchedule(scheduleEntityList: List<ScheduleEntity>) =
        f1Dao.insertSchedule(scheduleEntityList)

    suspend fun insertDriverStandings(driverEntityList: List<DriverEntity>) =
        f1Dao.insertDriver(driverEntityList)

    suspend fun insertConstructorStandings(constructorEntityList: List<ConstructorEntity>) =
        f1Dao.insertConstructor(constructorEntityList)


    suspend fun getSchedule() = f1Dao.getSchedule()

    suspend fun getDriverStandings() = f1Dao.getDriverStandings()

    suspend fun getConstructorStandings() = f1Dao.getConstructorStandings()*/

}