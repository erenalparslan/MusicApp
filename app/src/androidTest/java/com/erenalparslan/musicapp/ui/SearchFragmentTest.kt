package com.erenalparslan.musicapp.ui


import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.chnouman.lastfmapidemo.core.utils.launchFragmentInHiltContainer
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.presentation.search.SearchFragment
import org.junit.Before
import org.junit.Test

class SearchFragmentTest {


    @Before
    fun setup() {

    launchFragmentInHiltContainer<SearchFragment> {  }

    }

    @Test
    fun editTextDisplayTest(){

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun searchButtonDisplayTest(){
        Espresso.onView(ViewMatchers.withId(R.id.searchButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun artistRecyclerDisplayTest(){
        Espresso.onView(ViewMatchers.withId(R.id.artistsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun typeTextTest(){
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText)).perform(ViewActions.typeText("simge"))
        Espresso.onView(ViewMatchers.withId(R.id.searchButton)).perform(click())


    }
}