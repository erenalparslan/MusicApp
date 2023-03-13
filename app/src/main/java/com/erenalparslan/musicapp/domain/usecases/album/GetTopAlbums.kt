package com.erenalparslan.musicapp.domain.usecases.album

import com.erenalparslan.musicapp.BuildConfig
import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopAlbums(private val repository: TopAlbumRepository) {

    suspend operator fun invoke(word: String): Flow<Resource<List<Album>>> {
        return if (word.isEmpty()) {
            flow { }
        } else {
            return repository.getTopAlbums(word, BuildConfig.API_KEY)
        }
    }
}
