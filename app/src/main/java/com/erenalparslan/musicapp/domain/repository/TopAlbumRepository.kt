package com.erenalparslan.musicapp.domain.repository

import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.data.local.entities.Track
import kotlinx.coroutines.flow.Flow

interface TopAlbumRepository {
    suspend fun getTopAlbums(query: String, apiKey: String): Flow<Resource<List<Album>>>
    suspend fun getAlbumInfo(
        artist: String,
        album: String,
        apiKey: String
    ): Flow<Resource<List<Track>>>

    suspend fun compareLocalAlbums(albums: List<Album>): List<Album>
    suspend fun getLocalTracks(albumName: String): Flow<Resource<List<Track>>>
    suspend fun addAlbum(albumsDto: Album)
    suspend fun addTracks(tracks: List<Track>)
    suspend fun addArtist(artist: com.erenalparslan.musicapp.data.local.entities.Artist)
    suspend fun deleteAlbum(albumsDto: Album)
    suspend fun deleteTracks(albumsName: String)
    suspend fun deleteArtist(artist: String)
}
