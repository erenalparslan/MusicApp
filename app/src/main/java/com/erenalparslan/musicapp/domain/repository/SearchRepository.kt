package com.erenalparslan.musicapp.domain.repository

import com.erenalparslan.musicapp.data.remote.models.ArtistsSearchResponse

interface SearchRepository {
    suspend fun searchArtistPaged(
        artistQuery: String,
        apiKey: String,
        page: Int,
        limit: Int
    ): ArtistsSearchResponse
}
