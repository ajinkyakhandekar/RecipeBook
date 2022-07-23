package com.tghc.recipebook.extention

import android.os.Handler
import android.util.Log
import com.google.gson.Gson
import com.tghc.recipebook.constant.Status
import com.tghc.recipebook.constant.TAG
import com.tghc.recipebook.data.response.BaseResponse

fun log(tag: String, message: String) {
    Log.v(TAG, "$tag : $message")
}

fun log(tag: String, message: Int) {
    Log.v(TAG, "$tag : $message")
}

val Any.jsonString: String
    get() = try {
        Gson().toJson(this)
    } catch (e: Exception) {
        ""
    }

fun runDelayed(delay: Long, action: () -> Unit) {
    Handler().postDelayed(action, delay)
}

fun isEmpty(data: String?): Boolean {
    return if (data == null)
        true
    else data == ""
}

inline fun catchAll(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun toggleCondition(flag: Boolean, flagTrue: () -> Unit, flagFalse: () -> Unit) {
    if (flag) {
        flagTrue()
    } else {
        flagFalse()
    }
}

fun splitStringToList(input: String?): List<String> {
    return input?.split(",")?.map { it.trim() } ?: ArrayList()
}


fun <T : Any> BaseResponse<T?>.response(success: (data: T) -> Unit, fail:(msg: String) -> Unit) {
    when (status) {
        Status.SUCCESS -> {
            data?.let {
                success(it)
            }
        }
        Status.ERROR -> {
            error?.let {
                fail(it)
            }
        }
        Status.LOADING -> {
            //progressDialog.show()
        }
        Status.OFFLINE -> {
            //networkDialog = Visibility.VISIBLE
        }
    }
}

