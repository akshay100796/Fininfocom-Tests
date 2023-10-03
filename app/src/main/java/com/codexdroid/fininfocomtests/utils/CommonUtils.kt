package com.codexdroid.fininfocomtests.utils

import com.google.gson.GsonBuilder


fun toJson(data: Any?): String? = GsonBuilder().serializeNulls().create().toJson(data)

fun String.isValidPassword(): Boolean {

    val capitalLetterRegex = Regex(".*[A-Z].*")
    val smallLetterRegex = Regex(".*[a-z].*")
    val digitRegex = Regex(".*\\d.*")
    val specialCharRegex = Regex(".*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>?].*")

    return (matches(capitalLetterRegex) &&
            matches(smallLetterRegex) &&
            matches(digitRegex) &&
            matches(specialCharRegex))
}