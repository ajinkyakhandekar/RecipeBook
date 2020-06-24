package com.tghc.recipebook.data.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Ingredient(
        var ingredient: String="",
        var num: String="",
        var type: String=""
): Serializable
