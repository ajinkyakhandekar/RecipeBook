package com.tghc.recipebook.data.response

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.tghc.recipebook.constant.Status
import com.tghc.recipebook.extention.log

data class BaseResponse<out T>(val status: Status, val data: T?, val error: String?) {

    companion object {
        fun <T> success(data: T?): BaseResponse<T> {
            log("response recipe", data.toString())
            return BaseResponse(Status.SUCCESS, data, null)
        }

        fun <T> loading(): BaseResponse<T> {
            return BaseResponse(Status.LOADING, null, null)
        }

        fun <T> error(error: String?): BaseResponse<T> {
            return BaseResponse(Status.ERROR, null, error)
        }

        fun <T> offline(): BaseResponse<T> {
            return BaseResponse(Status.OFFLINE, null, null)
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        if (capabilities != null) when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }

        return false
    }
}