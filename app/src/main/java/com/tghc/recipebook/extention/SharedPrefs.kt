package com.tghc.recipebook.extention

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.tghc.recipebook.constant.KEY_SHARED_PREFERENCE

private fun sharedPref(context: Context): SharedPreferences {
    return context.getSharedPreferences(KEY_SHARED_PREFERENCE, Context.MODE_PRIVATE)
}

private fun editor(context: Context): SharedPreferences.Editor {
    return sharedPref(context).edit()
}

fun Fragment.setPreference(key: String, value: Boolean) {
    val editor = editor(requireContext())
    editor.putBoolean(key, value)
    editor.apply()
}

fun Fragment.setPreference(key: String, value: String) {
    val editor = editor(requireContext())
    editor.putString(key, value)
    editor.apply()
}

fun Fragment.setPreference(key: String, value: Int) {
    val editor = editor(requireContext())
    editor.putInt(key, value)
    editor.apply()
}

fun Fragment.getBooleanPref(key: String): Boolean {
    return sharedPref(requireContext()).getBoolean(key, false)
}

fun Fragment.getStringPref(key: String): String {
    return sharedPref(requireContext()).getString(key, "").toString()
}

fun Fragment.getIntPref(key: String): Int {
    return sharedPref(requireContext()).getInt(key, 0)
}

fun Fragment.clear() {

    val editor = editor(requireContext())
    editor.clear()
    editor.apply()
}
