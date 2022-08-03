package com.tghc.recipebook.data.remote.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tghc.recipebook.common.COLLECTION_COUNTER
import com.tghc.recipebook.common.COLLECTION_RECIPE
import com.tghc.recipebook.data.remote.dto.CounterDto
import com.tghc.recipebook.data.remote.dto.RecipeDto
import kotlinx.coroutines.tasks.await


class FirebaseService(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) {

    suspend fun getRecipeListService(): List<RecipeDto> {
        return firebaseFirestore.collection(COLLECTION_RECIPE)
            .get().await().toObjects(RecipeDto::class.java)
    }

    suspend fun getRecipeService(recipeId: String): RecipeDto? {
        return firebaseFirestore.collection(COLLECTION_RECIPE).document(recipeId).get().await()
            .toObject(RecipeDto::class.java)
    }

    suspend fun postRecipeService(recipe: RecipeDto): String {
        return firebaseFirestore.collection(COLLECTION_RECIPE).add(recipe).await().id
    }

    suspend fun getRecipeCount(): Int {
        println("launched firebase")
        val counterDto = firebaseFirestore.collection(COLLECTION_COUNTER).document("1").get().await()
            .toObject(CounterDto::class.java)

        return counterDto?.count ?: 0
    }

    suspend fun deleteRecipeService(recipeId: String):Boolean {
        return try {
            firebaseFirestore.collection(COLLECTION_RECIPE).document(recipeId).delete().await()
            println("delete firebase success---------")
            true
        }catch (e: Exception){
            println("delete firebase failed------")
            false
        }
    }

}


/*
object FirebaseService {
    private val storage = FirebaseStorage.getInstance()
    private const val collection_recipe = "recipe"
    private const val timestamp = "timestamp"
    private const val pageSize = 10L

    suspend fun getRecipeListService(db: FirebaseFirestore, page: Int): MutableList<RecipeDto> {
        return db.collection(collection_recipe)
//            .orderBy(timestamp)
//            .startAfter(page * pageSize)
//            .limit(pageSize)
            .get().await().toObjects(RecipeDto::class.java)
    }

    suspend fun getRecipeService(db: FirebaseFirestore, recipeId: String): RecipeDto? {
        return db.collection(collection_recipe).document(recipeId).get().await()
            .toObject(RecipeDto::class.java)
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

    suspend fun postRecipeService(db: FirebaseFirestore, recipe: RecipeDto): String {
        return db.collection(collection_recipe).add(recipe).await().id
    }

    suspend fun postRecipeListService(
        db: FirebaseFirestore,
        recipeList: MutableList<RecipeDto>
    ): String {
        return db.collection(collection_recipe).add(recipeList).await().id
    }


    suspend fun deleteRecipeService(
        db: FirebaseFirestore,
        recipeId: String
    ): Boolean {
        db.collection(collection_recipe).document(recipeId).delete().await()
        return true
    }

}*/
