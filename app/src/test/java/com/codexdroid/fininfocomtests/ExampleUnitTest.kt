package com.codexdroid.fininfocomtests

import com.codexdroid.fininfocomtests.utils.isValidPassword
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    //NOTE: PASS
    @Test
    fun checkPass() {
        //val pass = "Akshay@123"
        val pass = "Fin@123"
        val expected = true
        val actual = pass.isValidPassword()
        Assert.assertEquals(expected, actual)
    }
}