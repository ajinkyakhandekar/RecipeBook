package com.tghc.recipebook.extention

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.toast(data: Any?) {
    Toast.makeText(requireContext(), data.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.fragmentBackPressed(backPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressed()
            }
        })
}

fun Fragment.showAlertDialog(
    message: String?=null, isCancelable: Boolean = false, isCancelableTouchOutside: Boolean = false,
    builderFunction: AlertDialog.Builder.() -> Any
) : Dialog {
    val builder = AlertDialog.Builder(requireContext())
    builder.builderFunction()
    val dialog = builder.create()

    dialog.setMessage(message)
    dialog.setCancelable(isCancelable)
    dialog.setCanceledOnTouchOutside(isCancelableTouchOutside)
    dialog.show()

    return dialog
}

//----------------------- Navigation -----------------------//

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}
