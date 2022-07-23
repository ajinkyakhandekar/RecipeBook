package com.tghc.recipebook.dataconverter

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.tghc.recipebook.constant.MSG_FIREBASE_ERROR
import com.tghc.recipebook.constant.MSG_RECIPE_SAVED
import com.tghc.recipebook.constant.TAG
import com.tghc.recipebook.data.model.Recipe
import com.tghc.recipebook.data.viewmodel.FirebaseViewModel
import com.tghc.recipebook.extention.log
import com.tghc.recipebook.extention.response
import com.tghc.recipebook.extention.toast
import kotlinx.coroutines.*

class ConverterActivity : AppCompatActivity() {

    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private val recipeList = mutableListOf<Recipe>()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonText = this.assets.readJsonFile("old_db.json")
        val oldDb = gson.fromJson(jsonText, OldDb::class.java)

        recipeList.clear()

        oldDb?.allData?.forEach {
            val recipeOld = gson.fromJson(it.RbData, RecipeOld::class.java)
            val recipe = mapData(recipeOld)
            recipeList.add(recipe)
        }
        recipeList.reverse()

        /*CoroutineScope(Dispatchers.Main).launch {
            recipeList.forEach {
                delay(2000)
               // uploadRecipe(it)
            }
        }*/
    }

    private fun uploadRecipe(recipe: Recipe) {
        firebaseViewModel.postRecipe(FirebaseFirestore.getInstance(), recipe)
            .observe(this) { baseResponse ->
                baseResponse.response({
                    println(it)
                }, {
                    toast(MSG_FIREBASE_ERROR)
                })
            }
    }


    private fun mapData(recipeOld: RecipeOld) = Recipe().apply {
        id = recipeOld.id
        cTime = recipeOld.cTime
        cuisine = recipeOld.cuisine
        notes = recipeOld.notes
        pTime = recipeOld.pTime
        servings = recipeOld.servings
        title = recipeOld.title
        type = recipeOld.type
        imagePath.addAll(recipeOld.images)
        ingredient.addAll(recipeOld.ingredients)
        procedure.addAll(recipeOld.procedure)
        tags = recipeOld.tags
        chef = when {
            recipeOld.desc.contains("Jyoti Ajinkya Khandekar") ->"Jyoti Ajinkya Khandekar"
            recipeOld.desc.contains("Vasanti Ghanshyam Patekar") ->"Vasanti Ghanshyam Patekar"
            recipeOld.desc.contains("Jyoti Ghanshyam Patekar") ->"Jyoti Ghanshyam Patekar"
            recipeOld.desc.contains("Sangita Khandekar") ->"Sangita Khandekar"
            recipeOld.desc.contains("Ajinkya Khandekar") ->"Ajinkya Khandekar"
            recipeOld.desc.contains("Sangeeta Narayan Patekar") ->"Sangeeta Narayan Patekar"
            else -> ""
        }
        recipeOld.images.forEach {
            imageId.add(it.substringAfterLast("/"))
        }
    }
}


fun AssetManager.readJsonFile(fileName: String) =
    open(fileName).bufferedReader().use { it.readText() }

