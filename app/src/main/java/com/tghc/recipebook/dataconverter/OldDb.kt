package com.tghc.recipebook.dataconverter

import com.tghc.recipebook.data.model.Ingredient

data class OldDb(
    val allData: MutableList<Rb> = ArrayList()
)

data class Rb(
    var Id: Long = 0,
    var RbData: String = ""
)

data class RecipeOld(
    val cTime: String,
    val cuisine: String,
    val desc: String,
    val id: String,
    val images: MutableList<String>,
    val ingredients: MutableList<Ingredient>,
    val notes: String,
    val pTime: String,
    val procedure: MutableList<String>,
    val servings: String,
    val tags: MutableList<String>,
    val title: String,
    val type: String
)
