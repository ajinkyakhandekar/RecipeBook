package com.tghc.recipebook.extention

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tghc.recipebook.constant.Direction
import com.tghc.recipebook.ui.adapter.RecyclerAdapter

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.toggleVisibility(): View {
    if (visibility == View.VISIBLE) {
        visibility = View.INVISIBLE
    } else {
        visibility = View.INVISIBLE
    }
    return this
}

fun <T : View> T.click(block: (T) -> Unit) {
    setOnClickListener { block(it as T) }
}

fun <T:Any> View.clickHolder(holder: RecyclerAdapter.ViewHolder<T>, block: (Int) -> Unit) {
    setOnClickListener { block(holder.adapterPosition) }
}

fun Fragment.inflate1(layoutRes: Int):View{
    return LayoutInflater.from(requireContext()).inflate(layoutRes, null, false)
}

/**
 * Extension method to inflate layout for ViewGroup.
 */
fun ViewGroup.inflate1(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


fun View.mSetPadding(direction: Direction, padding:Int){
    when(direction){
        Direction.START->{
            setPadding(padding,paddingTop, paddingEnd,paddingBottom)
        }
        Direction.TOP->{
            setPadding(paddingStart,padding, paddingEnd,paddingBottom)
        }
        Direction.END->{
            setPadding(paddingStart,paddingTop, padding,paddingBottom)
        }
        Direction.BOTTOM->{
            setPadding(paddingStart,paddingTop, paddingEnd,padding)
        }
    }
}

