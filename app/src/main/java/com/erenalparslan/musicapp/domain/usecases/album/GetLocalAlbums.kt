package com.erenalparslan.musicapp.domain.usecases.album

import androidx.paging.PagingSource
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.repository.MainRepository

class GetLocalAlbums(private val repository: MainRepository) {
    operator fun invoke(): PagingSource<Int, Album> {
        return repository.getLocalAlbums()
    }
}
