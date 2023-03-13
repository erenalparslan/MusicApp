package com.erenalparslan.musicapp.domain.repository

import androidx.paging.PagingSource
import com.erenalparslan.musicapp.data.local.entities.Album

interface MainRepository {
    fun getLocalAlbums(): PagingSource<Int, Album>
}
