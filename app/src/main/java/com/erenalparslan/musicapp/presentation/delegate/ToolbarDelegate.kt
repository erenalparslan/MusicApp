package com.erenalparslan.musicapp.presentation.delegate

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

/**
 * Generic Toolbar Delegate to implement toolbar with App activities
 * */
interface ToolbarDelegate {
    fun setupToolbar(activity: AppCompatActivity, toolbar: Toolbar, navController: NavController)
}

class ToolbarDelegateImpl : ToolbarDelegate {

    override fun setupToolbar(
        activity: AppCompatActivity,
        toolbar: Toolbar,
        navController: NavController
    ) {
        activity.setSupportActionBar(toolbar)
        val config = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(
            activity,
            navController,
            config
        )
        toolbar.setupWithNavController(
            navController,
            config
        )
    }
}
