package com.tghc.recipebook.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tghc.recipebook.data.modelRequest.Recipe
import kotlinx.coroutines.tasks.await

object FirebaseService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private const val collection_recipe = "recipe"


    suspend fun getRecipeListService(username: String): Boolean {
        val documentSnapshot = db.collection(collection_recipe).document().get().await()
        return documentSnapshot.exists()
    }

    suspend fun postRecipeService(recipe: Recipe): Boolean {
        db.collection(collection_recipe).document().set(recipe).await()
        return true
    }

}