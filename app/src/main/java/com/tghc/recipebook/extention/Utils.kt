package com.tghc.recipebook.extention

import android.util.Log
import com.google.gson.Gson
import com.tghc.recipebook.common.TAG

fun Any?.log(message: Any? = null) =
    if (message == null) {
        Log.v(TAG, this.toString())
    } else {
        Log.v(TAG, "$message : $this")
    }

fun Any?.isNull() = this == null