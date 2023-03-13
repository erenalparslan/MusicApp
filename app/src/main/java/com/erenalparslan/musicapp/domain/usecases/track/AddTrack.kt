package com.erenalparslan.musicapp.domain.usecases.track

import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class AddTrack(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(tracks: List<Track>) {
        repository.addTracks(tracks)
    }
}
