package com.erenalparslan.musicapp.di

import com.erenalparslan.musicapp.domain.repository.MainRepository
import com.erenalparslan.musicapp.domain.repository.SearchRepository
import com.erenalparslan.musicapp.domain.repository.TopAlbumRepository
import com.erenalparslan.musicapp.domain.usecases.album.*
import com.erenalparslan.musicapp.domain.usecases.artist.AddArtist
import com.erenalparslan.musicapp.domain.usecases.artist.DeleteArtist
import com.erenalparslan.musicapp.domain.usecases.artist.SearchArtist
import com.erenalparslan.musicapp.domain.usecases.track.AddTrack
import com.erenalparslan.musicapp.domain.usecases.track.DeleteTracks
import com.erenalparslan.musicapp.domain.usecases.track.GetLocalTracks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideGetAlbums(repository: MainRepository): GetLocalAlbums {
        return GetLocalAlbums(repository)
    }

    @Provides
    @Singleton
    fun provideSearchArtistUseCase(repository: SearchRepository): SearchArtist {
        return SearchArtist(repository)
    }

    @Provides
    @Singleton
    fun provideGetLocalTracks(repository: TopAlbumRepository): GetLocalTracks {
        return GetLocalTracks(repository)
    }

    @Provides
    @Singleton
    fun provideAddTrack(repository: TopAlbumRepository): AddTrack {
        return AddTrack(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTracks(repository: TopAlbumRepository): DeleteTracks {
        return DeleteTracks(repository)
    }

    @Provides
    @Singleton
    fun provideGetAlbumInfo(repository: TopAlbumRepository): GetAlbumInfo {
        return GetAlbumInfo(repository)
    }

    @Provides
    @Singleton
    fun provideAddAlbum(repository: TopAlbumRepository): AddAlbum {
        return AddAlbum(repository)
    }

    @Provides
    @Singleton
    fun provideGetTopAlbums(repository: TopAlbumRepository): GetTopAlbums {
        return GetTopAlbums(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteAlbum(repository: TopAlbumRepository): DeleteAlbum {
        return DeleteAlbum(repository)
    }

    @Provides
    @Singleton
    fun provideAddArtist(repository: TopAlbumRepository): AddArtist {
        return AddArtist(repository)
    }

    @Provides
    @Singleton
    fun provideCompareLocalAlbums(repository: TopAlbumRepository): CompareLocalAlbums {
        return CompareLocalAlbums(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteArtist(repository: TopAlbumRepository): DeleteArtist {
        return DeleteArtist(repository)
    }
}
