package com.erenalparslan.musicapp.presentation.search.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erenalparslan.musicapp.R
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.databinding.ItemArtistBinding

class ArtistListAdapter(
    private var itemClick: (Artist) -> Unit
) : PagingDataAdapter<Artist, ArtistListAdapter.ArtistViewHolder>(
    CategoryMainDiffUtils()
) {
    var colors : Array<String> = arrayOf("#FEFBF3", "#F8F0DF")
    inner class ArtistViewHolder(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist,colors : Array<String>,position: Int) {
            this.apply {
                binding.apply {
                    artistNameTextView.text = artist.name
                    listenersTextView.text = artist.listeners
                    itemView.setOnClickListener {
                        itemClick.invoke(artist)
                    }
                    itemView.setBackgroundColor(Color.parseColor(colors[position%2]))
                    Glide
                        .with(artistImageView.context)
                        .load(artist.image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(artistImageView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding.inflate(
            LayoutInflater.from
                (parent.context),
            parent,
            false
        )
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, colors,position) }
    }

    class CategoryMainDiffUtils : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
