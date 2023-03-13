package com.erenalparslan.musicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.dao.ArtistDao
import com.erenalparslan.musicapp.data.local.dao.TrackDao
import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.data.local.entities.Track

@Database(
    entities = [Album::class, Track::class, Artist::class],
    version = 1,
    exportSchema = false
)
abstract class MusicAppDatabase : RoomDatabase() {
    abstract val albumDao: AlbumDao
    abstract val trackDao: TrackDao
    abstract val artisDao: ArtistDao
}
