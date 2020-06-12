package com.tghc.recipebook.extention

import android.os.Handler
import android.util.Log
import com.tghc.recipebook.constant.Status
import com.tghc.recipebook.constant.TAG
import com.tghc.recipebook.data.response.BaseResponse

fun log(tag: String, message: String) {
     Log.v(TAG, "$tag : $message")
}

fun log(tag: String, message: Int) {
    Log.v(TAG, "$tag : $message")
}

fun runDelayed(delay: Long, action: () -> Unit) {
    Handler().postDelayed(action, delay)
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

fun splitStringToList(input:String?): List<String> {
    return input?.split(",")?.map { it.trim() } ?: ArrayList()
}

inline fun <T : Any?> response(response: BaseResponse<T>, success: (data: T) -> Unit, fail: (msg: String) -> Unit ) {
    //progressDialog.dismiss()
    //networkDialog = Visibility.GONE
    when (response.status) {
        Status.SUCCESS -> {
            response.data?.let {
                success(it)
            }
        }
        Status.ERROR -> {
            response.error?.let {
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


