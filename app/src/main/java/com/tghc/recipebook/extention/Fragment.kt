package com.tghc.recipebook.extention

import android.app.Activity
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

//----------------------- Toast -----------------------//

fun Fragment.toast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

/*fun Fragment.toast2(message: String) {
    val layout = inflate1(requireContext(), R.layout.view_toast)
    val textToast = layout.findViewById<TextView>(R.id.text_toast)
    textToast.text = message

    val toast = Toast(requireContext())
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = layout
    toast.show()
}*/

//----------------------- System -----------------------//

fun Fragment.hideSoftKeyboard() {
    val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
}

fun Fragment.window(): Window {
    return requireActivity().window
}

fun Fragment.decorView(): View {
    return requireActivity().window.decorView
}

fun Fragment.treeObserver():ViewTreeObserver{
    return decorView().findViewById<View>(android.R.id.content).viewTreeObserver
}

fun Fragment.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else {
        0
    }
}

fun Fragment.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun Fragment.getScreenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}

fun Fragment.globalLayoutListener(globalListener: (r: Rect) -> Unit): ViewTreeObserver.OnGlobalLayoutListener {
    return ViewTreeObserver.OnGlobalLayoutListener {
        val r = Rect()
        val view1 = decorView()
        view1.getWindowVisibleDisplayFrame(r)
        globalListener(r)
    }
}

//----------------------- View -----------------------//

fun Fragment.create(@LayoutRes id: Int, container: ViewGroup?): View {
    return layoutInflater.inflate(id, container, false)
}

fun Fragment.hideStatusBar() {
    decorView().systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

fun Fragment.showStatusBar() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        window().statusBarColor = Color.GRAY
    } else {
        decorView().systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}


//----------------------- Navigation -----------------------//

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}

fun Fragment.navigateFinish(directions: NavDirections) {
    findNavController().navigate(directions)
    requireActivity().finish()
}