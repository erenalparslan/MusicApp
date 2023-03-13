package com.erenalparslan.musicapp.di

import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.dao.ArtistDao
import com.erenalparslan.musicapp.data.local.dao.TrackDao
import com.erenalparslan.musicapp.data.remote.MusicAppApi
import com.erenalparslan.musicapp.data.repository.MainRepositoryImpl
import com.erenalparslan.musicapp.data.repository.SearchRepositoryImpl
import com.erenalparslan.musicapp.data.repository.TopAlbumRepositoryImpl
import com.erenalparslan.musicapp.domain.repository.MainRepository
import com.erenalparslan.musicapp.domain.repository.SearchRepository
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(
        albumsDao: AlbumDao
    ): MainRepository {
        return MainRepositoryImpl(
            albumsDao
        )
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        MusicAppApi: MusicAppApi
    ): SearchRepository {
        return SearchRepositoryImpl(
            MusicAppApi
        )
    }

    @Provides
    @Singleton
    fun provideTopAlbumRepository(
        api: MusicAppApi,
        albumsDao: AlbumDao,
        trackDao: TrackDao,
        artistDao: ArtistDao
    ): TopAlbumRepository {
        return TopAlbumRepositoryImpl(api, albumsDao, trackDao, artistDao)
    }
}
