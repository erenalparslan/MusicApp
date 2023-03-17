package com.erenalparslan.musicapp.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.domain.usecases.album.DeleteAlbum
import com.erenalparslan.musicapp.domain.usecases.album.GetLocalAlbums
import com.erenalparslan.musicapp.domain.usecases.artist.DeleteArtist
import com.erenalparslan.musicapp.domain.usecases.track.DeleteTracks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalAlbums: GetLocalAlbums,
    val deleteTracks: DeleteTracks,
    val deleteAlbumUseCase: DeleteAlbum,
    val deleteArtist: DeleteArtist
) : ViewModel() {



    val data = Pager(
        PagingConfig(
            pageSize = 5,
            initialLoadSize = 5
        )
    ) {
        getLocalAlbums()
    }.flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)



    fun deleteAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                deleteTracks(album.name)
            }
            launch {
                deleteAlbumUseCase(album)
                deleteArtist(album.artistName)
            }
        }
    }







}
