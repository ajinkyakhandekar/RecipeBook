package com.tghc.recipebook.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tghc.recipebook.data.local.entity.RecipeEntity
import com.tghc.recipebook.data.local.entity.UserEntity

@Dao
interface RecipeDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRecipeList(RecipeEntityList: List<RecipeEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRecipe(RecipeEntity: RecipeEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(UserEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeList(RecipeEntityList: List<RecipeEntity>)

    @Query("SELECT * FROM recipe_table")
    fun getRecipeListLocal(): List<RecipeEntity>

    @Query("SELECT COUNT(*) FROM recipe_table")
    fun getRecipeCount():Int

//    @Query("SELECT recipe FROM Recipe_table where id = id")
//    suspend fun getRecipe(id : String): List<RecipeEntity>

//    @Query("SELECT * FROM User_table")
//    suspend fun getUser(): UserEntity

}