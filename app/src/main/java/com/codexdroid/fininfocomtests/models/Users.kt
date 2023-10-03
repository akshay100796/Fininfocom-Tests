package com.codexdroid.fininfocomtests.models


data class Users (
    val id: Int,
    val name: String,
    val age: Int,
    val city: String
) {
    constructor() : this(0,"",0,"")
}