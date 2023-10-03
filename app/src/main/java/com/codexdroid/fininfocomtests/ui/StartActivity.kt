package com.codexdroid.fininfocomtests.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codexdroid.fininfocomtests.R
import com.codexdroid.fininfocomtests.utils.PrefManager

class StartActivity : AppCompatActivity() {
    private val prefManager by lazy {
        PrefManager.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        when (Build.VERSION.SDK_INT) {

            //This is Min Version Android 10
            Build.VERSION_CODES.Q -> {
                Handler(Looper.getMainLooper()).postDelayed ({ toNext() }, 3000L)
            }
            Build.VERSION_CODES.R, Build.VERSION_CODES.S -> {
                installSplashScreen().setKeepOnScreenCondition { true }
                toNext()
            }
        }
    }

    private fun toNext() {

        prefManager.getToken()?.let {
            startActivity(Intent(this,HomeActivity::class.java))
        } ?: startActivity(Intent(this,LoginActivity::class.java))

        finish()
    }
}