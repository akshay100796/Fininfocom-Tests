package com.codexdroid.fininfocomtests.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NoConnectionInterceptor {

    fun isConnectionOn(context: Context): Boolean {
        return checkInternet(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
    private fun checkInternet(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || it.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }
}