package com.erenalparslan.musicapp.di

import android.app.Application
import androidx.room.Room
import com.erenalparslan.musicapp.data.local.MusicAppDatabase
import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.dao.ArtistDao
import com.erenalparslan.musicapp.data.local.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAlbumDao(MusicAppDatabase: MusicAppDatabase): AlbumDao {
        return MusicAppDatabase.albumDao
    }

    @Provides
    @Singleton
    fun provideTrackDao(wordDatabase: MusicAppDatabase): TrackDao {
        return wordDatabase.trackDao
    }

    @Provides
    @Singleton
    fun provideArtistDao(wordDatabase: MusicAppDatabase): ArtistDao {
        return wordDatabase.artisDao
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MusicAppDatabase {
        return Room.databaseBuilder(application, MusicAppDatabase::class.java, DB_NAME)
            .build()
    }

    private const val DB_NAME = "MusicApp_db"
}
