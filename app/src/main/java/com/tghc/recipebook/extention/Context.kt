package com.tghc.recipebook.extention

import android.content.Context
import android.widget.Toast

fun Context.toast(data: Any?) {
    Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
}
