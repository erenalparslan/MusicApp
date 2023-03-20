package com.erenalparslan.musicapp.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.erenalparslan.musicapp.presentation.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import com.erenalparslan.musicapp.R
import org.junit.Before

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup(){

        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun verifyTitle_is_Correct() {
        Espresso.onView(withId( R.id.main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(R.string.label_main_fragment))))
    }

    @Test
    fun verifyOptionMenu_is_Displayed() {
        Espresso.onView(withId(R.id.searchFragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.searchFragment)).perform(ViewActions.click())
    }
}

