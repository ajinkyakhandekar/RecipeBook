package com.tghc.recipebook.data.local.database

import androidx.room.TypeConverter
import com.tghc.recipebook.data.local.entity.IngredientEntity
import com.tghc.recipebook.extention.jsonString
import com.tghc.recipebook.extention.toList

class RecipeTypeConverter {

    @TypeConverter
    fun toIngredientList(jsonString: String) = jsonString.toList<IngredientEntity>()

    @TypeConverter
    fun toIngredientJsonString(ingredientEntityList: List<IngredientEntity>) =
        ingredientEntityList.jsonString

    @TypeConverter
    fun toStringList(jsonString: String) = jsonString.toList<String>()

    @TypeConverter
    fun toJsonString(entityList: List<String>) =
        entityList.jsonString

}