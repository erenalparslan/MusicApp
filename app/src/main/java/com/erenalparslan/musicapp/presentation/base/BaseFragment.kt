package com.erenalparslan.musicapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.erenalparslan.musicapp.core.util.Inflate

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val viewDataBinding: VB?
        get() = _binding as VB?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(layoutInflater)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
