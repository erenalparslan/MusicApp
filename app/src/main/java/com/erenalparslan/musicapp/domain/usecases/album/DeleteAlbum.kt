package com.erenalparslan.musicapp.domain.usecases.album

import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository

class DeleteAlbum(private val repository: TopAlbumRepository) {
    suspend operator fun invoke(albumsDto: Album) {
        return repository.deleteAlbum(albumsDto)
    }
}
