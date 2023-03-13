package com.erenalparslan.musicapp.domain.usecases.album

import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class CompareLocalAlbums(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(albums: List<Album>): List<Album> {
        return repository.compareLocalAlbums(albums)
    }
}
