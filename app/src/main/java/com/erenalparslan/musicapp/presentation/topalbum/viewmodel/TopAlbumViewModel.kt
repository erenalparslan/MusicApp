package com.erenalparslan.musicapp.presentation.topalbum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.musicapp.core.util.Resource
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.domain.usecases.album.*
import com.erenalparslan.musicapp.domain.usecases.artist.AddArtist
import com.erenalparslan.musicapp.domain.usecases.artist.DeleteArtist
import com.erenalparslan.musicapp.domain.usecases.track.AddTrack
import com.erenalparslan.musicapp.domain.usecases.track.DeleteTracks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumViewModel @Inject constructor(
    val addTrack: AddTrack,
    val getAlbumInfo: GetAlbumInfo,
    val getTopAlbumsUseCase: GetTopAlbums,
    val compareLocalAlbums: CompareLocalAlbums,
    val deleteAlbumUseCase: DeleteAlbum,
    val addAlbumUseCase: AddAlbum,
    val addArtist: AddArtist,
    val deleteTracks: DeleteTracks,
    val deleteArtist: DeleteArtist
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getTopAlbums(artist: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getTopAlbumsUseCase(artist)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                val compareLocalAlbums1 = compareLocalAlbums(it)
                                _eventFlow.emit(UIEvent.Success(compareLocalAlbums1))
                            }
                        }
                        is Resource.Error -> {
                            result.message?.let { UIEvent.Error(it) }?.let {
                                _eventFlow.emit(
                                    it
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _eventFlow.emit(UIEvent.Loading)
                        }
                    }
                }.launchIn(this)
        }
    }

    fun saveAlbum(position: Int, albumsDto: Album, artist: Artist) {
        viewModelScope.launch(Dispatchers.IO) {
            // load the album detail
            val tracks = getAlbumInfo(artist.name, albumsDto.name)
            val job1 = launch { addArtist(artist) }
            val job2 = launch { addAlbumUseCase(albumsDto) }
            val job3 = launch {
                tracks.collectLatest {
                    it.data?.let { it1 -> addTrack(it1) }
                }
            }
            job1.join()
            job2.join()
            job3.join()
            _eventFlow.emit(UIEvent.ItemSaved(position))
        }
    }

    fun deleteAlbum(album: Album, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val job1 = launch {
                deleteTracks(album.name)
            }
            val job2 = launch {
                deleteAlbumUseCase(album)
                deleteArtist(album.artistName)
            }
            job1.join()
            job2.join()
            _eventFlow.emit(UIEvent.ItemDeleted(position))
        }
    }

    sealed class UIEvent {
        object Loading : UIEvent()
        data class Success(val artists: List<Album>?) : UIEvent()
        data class ItemSaved(val position: Int) : UIEvent()
        data class ItemDeleted(val position: Int) : UIEvent()
        data class Error(val message: String) : UIEvent()
    }
}
