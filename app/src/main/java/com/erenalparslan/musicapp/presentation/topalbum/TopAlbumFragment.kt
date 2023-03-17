package com.erenalparslan.musicapp.presentation.topalbum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.erenalparslan.musicapp.databinding.FragmentTopalbumsBinding
import com.erenalparslan.musicapp.presentation.base.BaseFragment
import com.erenalparslan.musicapp.presentation.topalbum.adapter.TopAlbumListAdapter
import com.erenalparslan.musicapp.presentation.topalbum.viewmodel.TopAlbumViewModel
import com.erenalparslan.musicapp.util.ItemMoveCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TopAlbumFragment : BaseFragment<FragmentTopalbumsBinding>(FragmentTopalbumsBinding::inflate) {
    private val viewModel: TopAlbumViewModel by viewModels()
    private val args by navArgs<TopAlbumFragmentArgs>()
    private val adapter: TopAlbumListAdapter by lazy {
        TopAlbumListAdapter({
            findNavController().navigate(
                TopAlbumFragmentDirections.actionTopAlbumFragmentToDetailFragment(
                    it
                )
            )
        }, { position ->
            adapter.currentList[position]?.apply {
                if (isDownloaded) {
                    viewModel.deleteAlbum(this, position)
                } else {
                    viewModel.saveAlbum(position, this, args.artist)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewDataBinding?.apply {
            albumsRecyclerView.adapter = adapter
            lifecycleScope.launch {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is TopAlbumViewModel.UIEvent
                        .Loading -> {
                        }
                        is TopAlbumViewModel.UIEvent.Success -> {
                            event.artists?.let { adapter.submitList(it) }
                        }
                        is TopAlbumViewModel.UIEvent.ItemSaved -> {

                            adapter.currentList.toMutableList().apply {
                                this[event.position] =
                                    this[event.position].copy(isDownloaded = true)
                                adapter.submitList(this)
                            }
                        }
                        is TopAlbumViewModel.UIEvent.ItemDeleted -> {
                            adapter.currentList.toMutableList().apply {
                                this[event.position] =
                                    this[event.position].copy(isDownloaded = false)
                                adapter.submitList(this)
                            }
                        }
                        is TopAlbumViewModel.UIEvent.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "error: ${event.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
        viewModel.getTopAlbums(args.artist.name)
    }
}
