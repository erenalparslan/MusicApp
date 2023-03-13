package com.erenalparslan.musicapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.erenalparslan.musicapp.domain.usecases.artist.SearchArtist
import com.erenalparslan.musicapp.presentation.search.paging.MusicAppPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtist: SearchArtist
) : ViewModel() {
    fun searchArtistPaged(artistQuery: String): Flow<PagingData<com.erenalparslan.musicapp.data.local.entities.Artist>> {
        if (artistQuery.isEmpty()) return flow { }
        return Pager(
            PagingConfig(
                pageSize = 1
            )
        ) {
            MusicAppPagingSource(artistQuery, searchArtist)
        }.flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)
    }
}
