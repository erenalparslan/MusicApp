package com.erenalparslan.musicapp

import com.erenalparslan.musicapp.data.local.entities.Album
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.data.local.entities.Track

object DataGenerator {
    fun getAlbum(name: String): Album {
        return Album(
            name,
            0,
            "https://lastfm.com",
            "https://lastfm.com",
            "ABC " + kotlin.random.Random.nextInt(),
            false
        )
    }

    fun getArtist(name: String): Artist {
        return Artist(name, "https://lastfm.com", "250", "http://lastfm.com")
    }

    fun getTrack(name: String): Track {
        return Track(name, 0, "https://lastfm.com", "albumName")
    }
}
