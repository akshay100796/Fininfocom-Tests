package com.codexdroid.fininfocomtests.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.codexdroid.fininfocomtests.R
import com.codexdroid.fininfocomtests.databinding.ActivityLoginBinding
import com.codexdroid.fininfocomtests.utils.AppConstants
import com.codexdroid.fininfocomtests.utils.PrefManager
import com.codexdroid.fininfocomtests.utils.isValidPassword

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel : LoginViewModel by viewModels()
    private val prefManager: PrefManager by lazy { PrefManager.getInstance(this) }

    private lateinit var username: String
    private lateinit var password: String

    private val onBackPress: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        onBackPressedDispatcher.addCallback(onBackPress)
        requestSetListener()
        requestObserver()
    }

    private fun requestSetListener() {
        binding.idButtonLogin.setOnClickListener {
            username = binding.idEditUsername.text.toString()
            password = binding.idEditPassword.text.toString()

            val isValidUsername = username.isNotEmpty() && username.length == 10
            Log.d("AXE","$isValidUsername | ${username.isNotEmpty()} || ${username.length == 7}")

            loginViewModel.requestShowError(
                AppConstants.Errors.USERNAME,
                if(!isValidUsername)
                    getString(R.string.apologized_invalid_username) else "")
            if(!isValidUsername) return@setOnClickListener

            loginViewModel.requestShowError(
                AppConstants.Errors.PASSWORD,
                if(!password.isValidPassword())
                    getString(R.string.apologized_invalid_password) else ""
            )
            if(!password.isValidPassword()) return@setOnClickListener

            val isCorrectUsername = username == AppConstants.LOGIN.USERNAME
            loginViewModel.requestShowError(
                AppConstants.Errors.USERNAME,
                if(!isCorrectUsername) getString(R.string.apologized_invalid_username) else "")
            if(!isCorrectUsername) return@setOnClickListener

            val isCorrectPassword = password == AppConstants.LOGIN.PASSWORD
            loginViewModel.requestShowError(AppConstants.Errors.PASSWORD,
                if(!isCorrectPassword) getString(R.string.apologized_invalid_password) else "")
            if(!isCorrectPassword) return@setOnClickListener

            prefManager.saveToken("eyJzdWIiOiJGaW5pbmZvY29tIiwibmFtZSI6IkZpbkAxMjMiLCJpYXQiOjE1MTYyMzkwMjJ9.P6uiSafgSPEWQJ9B4GLOCaKiWHIlFkdTzHn3JZ3Zea4")

            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

    private fun requestObserver() {
        loginViewModel.errorType.observe(this) {

            binding.idErrorTextUsername.text = it.second
            binding.idErrorTextUsername.visibility = if(it.first == AppConstants.Errors.USERNAME) View.VISIBLE else View.GONE

            binding.idErrorTextPassword.text = it.second
            binding.idErrorTextPassword.visibility = if(it.first == AppConstants.Errors.PASSWORD) View.VISIBLE else View.GONE
        }
    }
}

//TODO ("Observer")