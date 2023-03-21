package com.erenalparslan.musicapp.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.presentation.MainActivity
import com.erenalparslan.musicapp.presentation.main.adapter.AlbumListAdapter
import org.junit.Before
import org.junit.Test

class TopAlbumFragmentTest {

    @Before
    fun setup() {

        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.searchFragment)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText)).perform(ViewActions.typeText("simge"))
        Espresso.onView(ViewMatchers.withId(R.id.searchButton)).perform(ViewActions.click())
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.artistsRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.AlbumViewHolder>(
                0,
                ViewActions.click()
            )
        )
    }

    @Test
    fun topAlbumDisplayTest(){

        Espresso.onView(ViewMatchers.withId(R.id.albumsRecyclerView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()))

    }
}