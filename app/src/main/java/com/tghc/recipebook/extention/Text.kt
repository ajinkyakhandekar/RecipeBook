package com.tghc.recipebook.extention

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlin.random.Random

fun View.getString(stringResId: Int): String {
    return resources.getString(stringResId)
}

fun EditText.getString(): String {
    return if (text.toString().isNotEmpty()) text.toString().trim()
    else ""
}

fun EditText.isEmpty(): Boolean {
    return text.toString().isEmpty()
}

fun TextView.isEmpty(): Boolean {
    return text.toString().isEmpty()
}

fun TextView.getString(): String {
    return if (text.toString().isNotEmpty()) text.toString().trim()
    else ""
}

fun TextView.setString(data: String) {
    text = if (data.isNotEmpty()) data
    else ""
}

fun TextView.setDisplayText( data: String) {
    if (!TextUtils.isEmpty(text))
        text = data
    else hide()
}

fun TextView.setHashtag(s:String) {
    val data = splitStringToList(s)
    var t = ""
    data.forEach {
        if (!TextUtils.isEmpty(it)) t = "$t#$it "
    }
    text = t
}

fun TextView.setLeftDrawable(res: Int) {
    setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0)
}

fun RadioGroup.getString(): String {
    val radioButton: RadioButton = findViewById(checkedRadioButtonId)
    return if (!TextUtils.isEmpty(radioButton.text.toString())) radioButton.text.toString().trim()
    else ""
}



fun EditText.onDone(callback: () -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_GO
    maxLines = 1
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        false
    }
}

fun EditText.textWatcher(afterTextChanged: (text: String) -> Unit) {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }
    addTextChangedListener(textWatcher)
}