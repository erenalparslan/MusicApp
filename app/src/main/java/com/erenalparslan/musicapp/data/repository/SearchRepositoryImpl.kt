package com.erenalparslan.musicapp.data.repository

import com.erenalparslan.musicapp.data.remote.MusicAppApi
import com.erenalparslan.musicapp.data.remote.models.ArtistsSearchResponse
import com.erenalparslan.musicapp.domain.repository.SearchRepository

class SearchRepositoryImpl(private val api: MusicAppApi) : SearchRepository {
    override suspend fun searchArtistPaged(
        artistQuery: String,
        apiKey: String,
        page: Int,
        limit: Int
    ): ArtistsSearchResponse {
        return api.searchArtistPaged(artistQuery, apiKey, page, limit)
    }
}
