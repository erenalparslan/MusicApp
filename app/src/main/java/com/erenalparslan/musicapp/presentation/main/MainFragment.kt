package com.erenalparslan.musicapp.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.ItemTouchHelper
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.core.util.extensions.hide
import com.erenalparslan.musicapp.core.util.extensions.show
import com.erenalparslan.musicapp.databinding.FragmentMainBinding
import com.erenalparslan.musicapp.presentation.base.BaseFragment
import com.erenalparslan.musicapp.presentation.main.adapter.AlbumListAdapter
import com.erenalparslan.musicapp.presentation.main.paging.MainLoadStateAdapter
import com.erenalparslan.musicapp.presentation.main.viewmodel.MainViewModel
import com.erenalparslan.musicapp.presentationdelegate.OptionMenuDelegate
import com.erenalparslan.musicapp.presentationdelegate.OptionMenuDelegateImpl
import com.erenalparslan.musicapp.util.ItemMoveCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

/**
 * Populate the locally stored albums with pagination support
 * Also user can tap on delete button to delete the specific album
 * */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    OptionMenuDelegate by OptionMenuDelegateImpl() {
    private val viewModel: MainViewModel by viewModels()
    private var adapter: AlbumListAdapter? = null
    private lateinit var itemTouchHelperCallback: ItemMoveCallback
    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = AlbumListAdapter({
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    it
                )
            )
        }, { album ->
            viewModel.deleteAlbum(album)
        })
        setupOptionMenu(requireActivity(), this, viewLifecycleOwner)
        viewDataBinding?.apply {
            adapter?.addLoadStateListener { loadState ->
                manageLoadingState(loadState)
            }
            albumsRecyclerView.adapter = adapter?.withLoadStateFooter(MainLoadStateAdapter())
        }
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                viewDataBinding?.progressIndicator?.hide()
                adapter?.submitData(it)
            }
        }
    }

    private fun manageLoadingState(loadState: CombinedLoadStates) {
        viewDataBinding?.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    // Show first time loading UI
                    albumsRecyclerView.show()
                    emptyTextView.hide()
                    itemTouchHelperCallback = ItemMoveCallback(albumsRecyclerView.adapter!!)
                    itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
                    itemTouchHelper.attachToRecyclerView(albumsRecyclerView)
                }

                loadState.append is LoadState.Loading -> {
                    // Show little loading UI as we already have some items and we're loading
                }

                loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter?.itemCount!! < 1 -> {
                    // Show empty UI as there's no data available
                    albumsRecyclerView.hide()
                    emptyTextView.show()

                }

                loadState.refresh is LoadState.Error -> {
                    // Failed to load data in first try
                    emptyTextView.text = (loadState.refresh as LoadState.Error).error.message
                    emptyTextView.show()
                    albumsRecyclerView.hide()
                }

                loadState.append is LoadState.Error -> {
                    // Failed to load data while appending to existing items
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_more_records),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        viewDataBinding?.albumsRecyclerView?.adapter = null
        adapter = null
        super.onDestroyView()
    }


}
