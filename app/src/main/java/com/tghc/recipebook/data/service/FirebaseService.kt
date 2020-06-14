package com.tghc.recipebook.data.service

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tghc.recipebook.data.model.Item
import com.tghc.recipebook.data.model.Recipe
import kotlinx.coroutines.tasks.await
import java.io.File


object FirebaseService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private const val collection_recipe = "recipe"
    private const val collection_item = "item"
    private const val timestamp = "timestamp"
    private const val pageSize = 10L

    suspend fun getItemListService(page: Int): MutableList<Item> {
        return db.collection(collection_item)
//           .orderBy(timestamp)
//           .startAfter(page * pageSize)
//           .limit(pageSize)
            .get().await().toObjects(Item::class.java)
    }

    suspend fun getRecipeService(recipeId: String): Recipe? {
        return db.collection(collection_recipe).document(recipeId).get().await().toObject(Recipe::class.java)
    }

    suspend fun postImageService(path:String): String {
        val file = Uri.fromFile(File(path))
        val storageReference = storage.reference.child("images/${file.lastPathSegment}")
        storageReference.putFile(file).await()

        return storageReference.downloadUrl.toString()
    }

    suspend fun postRecipeService(recipe: Recipe): String {
        return db.collection(collection_recipe).add(recipe).await().id
    }

    suspend fun postItemService(item: Item): Boolean {
        db.collection(collection_item).add(item).await()
        return true
    }

    suspend fun deleteRecipeService(itemId:String, recipeId: String):Boolean{
        val recipe = db.collection(collection_recipe).document(recipeId)
        val item = db.collection(collection_item).document(itemId)

        db.runBatch {batch->
            batch.delete(recipe)
            batch.delete(item)
        }.await()

        return true
    }

}