package com.tghc.recipebook.data.modelRequest

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Keep
data class Recipe (
        @DocumentId
        var postId: String="",
        var userId:String="",
        var id: String="",
        var title: String="",
        var type: String="",
        var cuisine: String="",
        var cTime: String="",
        var pTime: String="",
        var desc: String="",
        var servings: String="",
        var notes: String="",
        var ingredients: MutableList<Ingredient>?= ArrayList(),
        var procedure: MutableList<String> = ArrayList(),
        var images: MutableList<String> = ArrayList(),
        var tags: MutableList<String> = ArrayList(),
        @ServerTimestamp
        var timestamp: Date = Date()
):Serializable
