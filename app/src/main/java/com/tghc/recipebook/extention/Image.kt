package com.tghc.recipebook.extention

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.ByteArrayOutputStream

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun ImageView.setRoundIcon(imageRes: Int) {
    Glide.with(context).load(imageRes).apply(RequestOptions().circleCrop()).into(this)
}

fun ImageView.loadFromUri(imageUri: Uri) {
    Glide.with(context).load(imageUri).into(this)
}

fun ImageView.setImage(@DrawableRes imageRes: Int) {
    Glide.with(context).load(imageRes).centerCrop().into(this)
}

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

fun ImageView.toggleSelector(flag: Boolean, @DrawableRes flagTrue: Int, @DrawableRes flagFalse: Int) {
    if (flag) {
        setImageResource(flagTrue)
    } else {
        setImageResource(flagFalse)
    }
}

fun Bitmap.resize(w: Number, h: Number): Bitmap {
    val width = width
    val height = height
    val scaleWidth = w.toFloat() / width
    val scaleHeight = h.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    if (width > 0 && height > 0) {
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}

fun Bitmap.getCompressed(): ByteArray {
    val baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 80, baos)
    return baos.toByteArray()
}

@Suppress("DEPRECATION")
fun Fragment.getBitmap(uri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    } else {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireActivity().contentResolver, uri))
    }
}

fun Fragment.getImagePath(uri: Uri): String? {
    var cursor = requireActivity().contentResolver.query(uri, null, null, null, null)
    cursor!!.moveToFirst()
    var documentId: String = cursor.getString(0)
    documentId = documentId.substring(documentId.lastIndexOf(":") + 1)
    cursor.close()

    cursor = requireActivity().contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
        MediaStore.Images.Media._ID + " = ? ", arrayOf(documentId), null
    )
    cursor!!.moveToFirst()
    val path: String = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
    cursor.close()
    return path
}