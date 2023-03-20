package com.erenalparslan.musicapp.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.presentation.main.MainFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DragTest {
    lateinit var scenerio: FragmentScenario<MainFragment>


    @Before
    fun setup() {
        scenerio = launchFragmentInContainer(themeResId = R.style.Theme_MusicApp)
        scenerio.moveToState(Lifecycle.State.STARTED)
    }


    @Test
    fun drag() {
// TODO: drag test will be written here
    }


}
