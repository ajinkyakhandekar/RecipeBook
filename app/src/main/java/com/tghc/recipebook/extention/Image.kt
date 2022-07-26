package com.tghc.recipebook.extention

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.setGlideImage(
    source: Any,
    @DrawableRes placeholder: Int? = null,
    isCircleCrop: Boolean = false,
    isCenterCrop: Boolean = false
) {
    val g = Glide.with(context).load(source)
    if (placeholder != null) {
        g.placeholder(placeholder)
    }
    if (isCircleCrop) {
        g.apply(RequestOptions().circleCrop())
    }
    if (isCenterCrop) {
        g.centerCrop()
    }
    g.into(this)
}