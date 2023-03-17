package com.erenalparslan.musicapp.presentation.detail.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.databinding.ItemTrackBinding
import kotlin.time.DurationUnit
import kotlin.time.toDuration



class TrackListAdapter(
    private var urlItemClick: (Track) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Track, TrackListAdapter.TrackViewHolder>(
    AlbumDiffUtils()
) {
    var colors : Array<String> = arrayOf("#FEFBF3", "#F8F0DF")
    inner class TrackViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track, position: Int,colors : Array<String>) {
            binding.apply {
                trackNumberTextView.text = position.plus(1).toString()
                trackNameTextView.text = track.name
                durationTextView.text =
                    track.duration.toDuration(DurationUnit.SECONDS).toString()
                urlImageView.setOnClickListener { urlItemClick.invoke(track) }
                itemView.setBackgroundColor(Color.parseColor(colors[position%2]))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = ItemTrackBinding.inflate(
            LayoutInflater.from
                (parent.context),
            parent,
            false
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position), position,colors)
    }

    class AlbumDiffUtils : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }
}
