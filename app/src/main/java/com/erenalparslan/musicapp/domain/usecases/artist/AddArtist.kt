package com.erenalparslan.musicapp.domain.usecases.artist

import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class AddArtist(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(artist: Artist) {
        repository.addArtist(artist)
    }
}
