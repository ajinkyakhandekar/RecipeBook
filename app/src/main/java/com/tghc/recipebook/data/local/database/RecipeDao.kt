package com.tghc.recipebook.data.local.database

import androidx.room.*
import com.tghc.recipebook.data.local.entity.RecipeEntity
import com.tghc.recipebook.data.local.entity.UserEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeList(RecipeEntityList: List<RecipeEntity>)

    @Query("SELECT * FROM recipe_table")
    fun getRecipeListLocal(): List<RecipeEntity>

    @Query("SELECT * FROM recipe_table WHERE recipeId = :recipeId")
    fun getRecipeLocal(recipeId : String): RecipeEntity

    @Query("SELECT COUNT(*) FROM recipe_table")
    fun getRecipeCount():Int

    @Query("DELETE FROM recipe_table WHERE recipeId = :recipeId")
    fun deleteRecipeLocal(recipeId: String) : Int


//    @Query("SELECT * FROM User_table")
//    suspend fun getUser(): UserEntity

}