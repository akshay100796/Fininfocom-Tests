package com.codexdroid.fininfocomtests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.codexdroid.fininfocomtests.ui.LoginActivity
import com.codexdroid.fininfocomtests.utils.AppConstants
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var activityScenario: ActivityScenario<LoginActivity>

    @Before
    fun setup() {
        // Launch the activity before each test
        activityScenario = ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.codexdroid.fininfocomtests", appContext.packageName)
    }

    @Test
    fun login() {

        val username = "finifocom"
        val pass = "Abc123@"

        //Should Fail : Yes, Failing
//        onView(withId(R.id.id_edit_username)).perform(typeText(username))
//        onView(withId(R.id.id_edit_password)).perform(typeText(pass))
//        Espresso.closeSoftKeyboard()
//        onView(withId(R.id.id_button_login)).perform(click())
//        val expected = true
//        val actual = username == AppConstants.LOGIN.USERNAME
//        assertEquals(expected, actual)


        //Should Passing: Yes, Passing
        val username1 = "Fininfocom"
        val pass1 = "Fin@123"
        onView(withId(R.id.id_edit_username)).perform(typeText(username1))
        onView(withId(R.id.id_edit_password)).perform(typeText(pass1))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.id_button_login)).perform(click())
        val expected1 = true
        val actual1 = username1 == AppConstants.LOGIN.USERNAME && pass1 == AppConstants.LOGIN.PASSWORD
        assertEquals(expected1, actual1)
    }
}