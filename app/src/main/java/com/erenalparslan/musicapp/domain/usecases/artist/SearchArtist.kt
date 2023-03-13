package com.erenalparslan.musicapp.domain.usecases.artist

import com.erenalparslan.musicapp.BuildConfig
import com.erenalparslan.musicapp.data.remote.models.ArtistsSearchResponse
import com.erenalparslan.musicapp.domain.repository.SearchRepository

class SearchArtist(private val repository: SearchRepository) {

    suspend operator fun invoke(
        artistQuery: String,
        page: Int,
        limit: Int
    ): ArtistsSearchResponse {
        return repository.searchArtistPaged(artistQuery, BuildConfig.API_KEY, page, limit)
    }
}
