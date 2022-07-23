package com.tghc.recipebook.extention

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import java.util.*

/*
fun Fragment.getCameraPermissions(granted:()-> Unit, failed:()->Unit ){
    val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    Permissions.check(requireActivity(), permissions, null, null, object : PermissionHandler() {
        override fun onGranted() {
            granted()
        }

        override fun onDenied(context: Context?, deniedPermissions: ArrayList<String>?) {
            super.onDenied(context, deniedPermissions)
            failed()
        }
    })
}*/
