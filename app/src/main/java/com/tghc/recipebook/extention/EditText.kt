package com.tghc.recipebook.extention

import android.widget.EditText

fun EditText.getString(): String {
    return if (text.toString().isNotEmpty()) text.toString().trim()
    else ""
}