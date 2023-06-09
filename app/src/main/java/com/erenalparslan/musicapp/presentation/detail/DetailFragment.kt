package com.erenalparslan.musicapp.presentation.detail

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.core.util.extensions.hide
import com.erenalparslan.musicapp.core.util.extensions.show
import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.databinding.FragmentDetailsBinding
import com.erenalparslan.musicapp.presentation.base.BaseFragment
import com.erenalparslan.musicapp.presentation.detail.adapter.TrackListAdapter
import com.erenalparslan.musicapp.presentation.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Populate the details of album with it's Artist and Track details
 * */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val adapter: TrackListAdapter by lazy {
        TrackListAdapter {
            Intent(ACTION_VIEW).apply {
                data = Uri.parse(it.url)
                startActivity(this)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            lifecycleScope.launch {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is DetailViewModel.UIEvent
                        .Loading -> {
                            progressIndicator.show()
                        }
                        is DetailViewModel.UIEvent.Success -> {
                            progressIndicator.hide()
                            event.artists?.let { setupAdapter(it) }
                        }
                        is DetailViewModel.UIEvent.Error -> {
                            progressIndicator.hide()
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
            artistTextView.text = args.album.artistName
            albumNameTextView.text = args.album.name
            playCountTextView.text = args.album.playCount.toString()
            Glide
                .with(requireActivity())
                .load(args.album.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(albumImageView)
            tracksRecyclerView.adapter = adapter
        }
        viewModel.getTracks(args.album.name, args.album.artistName)
    }

    private fun setupAdapter(artists: List<Track>) {
        adapter.submitList(artists)
    }
}
