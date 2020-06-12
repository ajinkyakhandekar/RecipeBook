package com.tghc.recipebook.extention

import android.text.TextUtils
import android.util.Patterns

fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhone(): Boolean {
    return if (this.length < 6) {
        false
    } else {
        !TextUtils.isEmpty(this) && Patterns.PHONE.matcher(this).matches()
    }
}