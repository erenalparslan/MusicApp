package com.erenalparslan.musicapp.domain.usecases.artist

import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class DeleteArtist(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(albumName: String) {
        return repository.deleteArtist(albumName)
    }
}
