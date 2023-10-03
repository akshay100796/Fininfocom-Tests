package com.codexdroid.fininfocomtests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private var _errorType= MutableLiveData<Pair<String, String>>()
    val errorType: LiveData<Pair<String, String>> = _errorType

    fun requestShowError(errorType: String, message: String) {
        _errorType.postValue(Pair(errorType, message))
    }
}