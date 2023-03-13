package com.erenalparslan.musicapp.data.repository

import androidx.paging.PagingSource
import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.repository.MainRepository

class MainRepositoryImpl(
    private val albumsDao: AlbumDao
) : MainRepository {
    override fun getLocalAlbums(): PagingSource<Int, Album> = albumsDao.getAlbumsList()
}
