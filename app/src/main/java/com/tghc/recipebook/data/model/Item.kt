package com.tghc.recipebook.data.model

import androidx.annotation.Keep
import com.google.firebase.firestore.ServerTimestamp
import java.util.*
import kotlin.collections.ArrayList

@Keep
data class Item(
    var recipeId: String="",
    var title: String = "",
    var cuisine: String = "",
    var chef: String = "",
    var images: MutableList<String> = ArrayList(),
    @ServerTimestamp
    var timestamp: Date = Date()
)