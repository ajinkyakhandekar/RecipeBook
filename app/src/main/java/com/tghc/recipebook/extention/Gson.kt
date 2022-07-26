package com.tghc.recipebook.extention

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val Any.jsonString: String
    get() = try {
        Gson().toJson(this)
    } catch (e: Exception) {
        ""
    }

inline fun <reified T> String.toList(): List<T> =
    Gson().fromJson<List<T>>(this, object : TypeToken<List<T>>() {}.type).orEmpty()