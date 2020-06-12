package com.tghc.recipebook.extention

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.fragmentBackPressed(backPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            backPressed()
        }
    })
}