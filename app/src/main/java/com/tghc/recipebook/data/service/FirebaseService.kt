package com.tghc.recipebook.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tghc.recipebook.data.model.Item
import com.tghc.recipebook.data.model.Recipe
import kotlinx.coroutines.tasks.await

object FirebaseService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private const val collection_recipe = "recipe"
    private const val collection_item = "item"
    private const val timestamp = "timestamp"
    private const val pageSize = 10L

    suspend fun getItemListService(page:Int): MutableList<Item> {
       return db.collection(collection_item)
//           .orderBy(timestamp)
//           .startAfter(page * pageSize)
//           .limit(pageSize)
           .get().await().toObjects(Item::class.java)
    }

    suspend fun getRecipeService(recipeId: String): Recipe? {
        return db.collection(collection_recipe).document(recipeId).get().await().toObject(Recipe::class.java)
    }

    suspend fun postRecipeService(recipe: Recipe): Boolean {
        db.collection(collection_recipe).document().set(recipe).await()
        return true
    }

}