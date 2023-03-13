package com.erenalparslan.musicapp.presentation

import androidx.navigation.findNavController
import com.erenalparslan.musicapp.R

import com.erenalparslan.musicapp.databinding.ActivityMainBinding
import com.erenalparslan.musicapp.presentation.base.BaseActivity
import com.erenalparslan.musicapp.presentation.delegate.ToolbarDelegate
import com.erenalparslan.musicapp.presentation.delegate.ToolbarDelegateImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    ToolbarDelegate by ToolbarDelegateImpl() {

    override fun initUserInterface() {
        setupToolbar(this, viewDataBinding.toolbar, findNavController(R.id.navHostFragment))
    }
}
