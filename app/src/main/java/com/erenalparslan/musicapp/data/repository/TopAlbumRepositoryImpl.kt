package com.erenalparslan.musicapp.data.repository

import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.dao.ArtistDao
import com.erenalparslan.musicapp.data.local.dao.TrackDao
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.data.local.entities.Track
import com.erenalparslan.musicapp.data.remote.MusicAppApi
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TopAlbumRepositoryImpl(
    private val api: MusicAppApi,
    private val albumDao: AlbumDao,
    private val trackDao: TrackDao,
    private val artistDao: ArtistDao
) :
    TopAlbumRepository {

    override suspend fun getTopAlbums(
        query: String,
        apiKey: String
    ): Flow<Resource<List<Album>>> =
        flow {
            emit(Resource.Loading())
            try {
                val albumsFromRemote = api.getTopAlbums(query, apiKey)
                val albums = albumsFromRemote.topalbums?.album?.map {
                    Album(
                        name = it.name ?: "",
                        url = it.url ?: "",
                        playCount = it.playcount ?: 0,
                        image = it.image?.last()?.text ?: "",
                        artistName = it.artist?.name ?: ""
                    )
                }
                emit(Resource.Success(albums))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = null
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = null
                    )
                )
            }
        }

    override suspend fun getAlbumInfo(
        artist: String,
        album: String,
        apiKey: String
    ): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading())
        try {
            val getAlbumInfo = api.getAlbumInfo(artist, album, apiKey)
            val tracks = getAlbumInfo.album?.tracks
            val tracksLocal = tracks?.tracks?.map {
                Track(it.name ?: "", it.duration ?: 0, it.url ?: "", album)
            }
            emit(Resource.Success(tracksLocal))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = null
                )
            )
        }
    }

    override suspend fun compareLocalAlbums(albums: List<Album>): List<Album> {
        albums.forEach {
            val exist = albumDao.isExist(it.name)
            if (exist) {
                it.isDownloaded = true
            }
        }
        return albums
    }

    override suspend fun getLocalTracks(albumName: String): Flow<Resource<List<Track>>> =
        flow {
            val tracks = trackDao.getAll(albumName)
            if (tracks.isEmpty()) {
                emit(Resource.Success(listOf()))
            } else {
                emit(Resource.Success(tracks))
            }
        }

    override suspend fun addAlbum(albumsDto: Album) = albumDao.insert(albumsDto)

    override suspend fun addTracks(tracks: List<Track>) = trackDao.insertAll(tracks)

    override suspend fun addArtist(artist: Artist) = artistDao.insert(artist)

    override suspend fun deleteAlbum(albumsDto: Album) = albumDao.delete(albumsDto.name)

    override suspend fun deleteTracks(albumsName: String) = trackDao.delete(albumsName)

    override suspend fun deleteArtist(artist: String) {
        // check if record is only attached to single album then delete it otherwise keep it
        var numberOfAlbums = 0
        albumDao.getAll().forEach {
            if (it.artistName == artist) {
                numberOfAlbums++
            }
        }
        if (numberOfAlbums == 0) {
            artistDao.delete(artist)
        }
    }
}
