package com.erenalparslan.musicapp.domain.usecases.album

import com.erenalparslan.musicapp.BuildConfig
import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumInfo(private val repository: TopAlbumRepository) {

    suspend operator fun invoke(artist: String, album: String): Flow<Resource<List<Track>>> {
        return repository.getAlbumInfo(artist, album, BuildConfig.API_KEY)
    }
}
