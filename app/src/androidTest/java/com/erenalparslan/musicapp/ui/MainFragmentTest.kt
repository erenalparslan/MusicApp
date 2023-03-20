package com.erenalparslan.musicapp.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.chnouman.lastfmapidemo.core.utils.launchFragmentInHiltContainer
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.presentation.main.MainFragment
import com.erenalparslan.musicapp.presentation.main.adapter.AlbumListAdapter
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class MainFragmentTest {

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInHiltContainer<MainFragment>(Bundle()) {
            navController.setGraph(R.navigation.main_nav_graph)
            navController.setCurrentDestination(R.id.mainFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

    }

    @Test
    fun checkAtStart_MainFragment_Loading() {

        // Verify that performing a click changes the NavController’s state
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.mainFragment)
    }

    @Test
    fun checkClicking_RecyclerViewItem_Opens_DetailFragment() {


        // Verify that performing a click changes the NavController’s state
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.mainFragment)
        Espresso.onView(ViewMatchers.withId(R.id.albumsRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.AlbumViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.detailFragment)
    }
}