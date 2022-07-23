package com.tghc.recipebook.data.service

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tghc.recipebook.data.model.Recipe
import kotlinx.coroutines.tasks.await

object FirebaseService {
    private val storage = FirebaseStorage.getInstance()
    private const val collection_recipe = "recipe"
    private const val timestamp = "timestamp"
    private const val pageSize = 10L

    suspend fun getRecipeListService(db: FirebaseFirestore, page: Int): MutableList<Recipe> {
        return db.collection(collection_recipe)
//            .orderBy(timestamp)
//            .startAfter(page * pageSize)
//            .limit(pageSize)
            .get().await().toObjects(Recipe::class.java)
    }

    suspend fun getRecipeService(db: FirebaseFirestore, recipeId: String): Recipe? {
        return db.collection(collection_recipe).document(recipeId).get().await()
            .toObject(Recipe::class.java)
    }

    suspend fun postImageService(uri: Uri): String {
        var storagePath: Uri? = null

        val storageReference = storage.reference.child("images/${uri.lastPathSegment}")
        storageReference.putFile(uri)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                storageReference.downloadUrl
            }
            .addOnCompleteListener { task1 ->
                if (task1.isSuccessful) {
                    storagePath = task1.result
                } else {

                }
            }.await()

        return storagePath.toString()
    }

    suspend fun postRecipeService(db: FirebaseFirestore, recipe: Recipe): String {
        return db.collection(collection_recipe).add(recipe).await().id
    }

    suspend fun postRecipeListService(db: FirebaseFirestore, recipeList: MutableList<Recipe>): String {
        return db.collection(collection_recipe).add(recipeList).await().id
    }


    suspend fun deleteRecipeService(
        db: FirebaseFirestore,
        recipeId: String
    ): Boolean {
        db.collection(collection_recipe).document(recipeId).delete().await()
        return true
    }

}