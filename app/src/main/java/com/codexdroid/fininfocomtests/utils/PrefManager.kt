package com.codexdroid.fininfocomtests.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager private constructor(private val context: Context) {

    private var sharePref: SharedPreferences? = null

    init {
        sharePref = context.getSharedPreferences(AppConstants.PREFERENCE.PREF_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        fun getInstance(context: Context) : PrefManager {
            return PrefManager(context)
        }
    }

    //Token
    fun saveToken(token: String) { sharePref?.edit()?.putString(AppConstants.PREFERENCE.TOKEN, token)?.apply() }
    fun getToken(): String? = sharePref?.getString(AppConstants.PREFERENCE.TOKEN, null)

}