package com.erenalparslan.musicapp.domain.usecases.track

import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import kotlinx.coroutines.flow.Flow

class GetLocalTracks(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(albumName: String): Flow<Resource<List<Track>>> {
        return repository.getLocalTracks(albumName)
    }
}
