package com.erenalparslan.musicapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.erenalparslan.musicapp.core.util.Inflate

abstract class BaseActivity<VB : ViewBinding>(private val inflate: Inflate<VB>) :
    AppCompatActivity() {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val viewDataBinding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initUserInterface()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected abstract fun initUserInterface()
}
