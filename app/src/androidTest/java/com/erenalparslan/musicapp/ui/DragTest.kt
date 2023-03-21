package com.erenalparslan.musicapp.ui

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chnouman.lastfmapidemo.core.utils.launchFragmentInHiltContainer
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.presentation.main.MainFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DragTest {
    private lateinit var navController: TestNavHostController


    @Before
    fun setup() {

        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MainFragment>(Bundle()) {

        }
    }


    @Test
    fun drag() {


    }


}
