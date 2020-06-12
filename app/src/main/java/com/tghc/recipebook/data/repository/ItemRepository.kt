package com.tghc.recipebook.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.tghc.recipebook.data.modelRequest.Recipe
import com.tghc.recipebook.data.response.ItemResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object ItemRepository {

    fun setItemList(recipe: Recipe): MutableLiveData<ItemResponse> {
        val itemResponse = MutableLiveData<ItemResponse>()
        itemResponse.value = ItemResponse.loading()
        val documentReference =
                if (TextUtils.isEmpty(recipe.postId)) {
                    FirebaseFirestore.getInstance().collection("posts").document()
                } else {
                    FirebaseFirestore.getInstance().collection("posts").document(recipe.postId)
                }

        documentReference.set(recipe)
                .addOnSuccessListener { task ->
                    itemResponse.value = ItemResponse.success(null)
                    //Toast.makeText(this, "Upload Successful !!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    itemResponse.value = ItemResponse.error(it)
                    it.printStackTrace()
                }
        return itemResponse
    }


   /* fun getItemList(context: Context): MutableLiveData<ItemResponse> {
        val itemResponse = MutableLiveData<ItemResponse>()
        if (!isOnline(context)) {
            itemResponse.value = ItemResponse.offline()
            return itemResponse
        }

        itemResponse.value = ItemResponse.loading()
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!
        FirebaseFirestore.getInstance().collection(Constant.posts)
                .whereEqualTo(Constant.userId, firebaseUser.uid)
                //.orderBy(Constant.FIELD_TIMESTAMP, Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    itemResponse.value = ItemResponse.success(querySnapshot.toObjects(Recipe::class.java))
                }
                .addOnFailureListener {
                    itemResponse.value = ItemResponse.error(it)
                    it.printStackTrace()
                }
        return itemResponse
    }
*/

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
        if (capabilities != null)
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }

        return false
    }
}