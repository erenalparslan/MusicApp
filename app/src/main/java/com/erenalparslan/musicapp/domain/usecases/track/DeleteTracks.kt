package com.erenalparslan.musicapp.domain.usecases.track

import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class DeleteTracks(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(albumName: String) {
        return repository.deleteTracks(albumName)
    }
}
