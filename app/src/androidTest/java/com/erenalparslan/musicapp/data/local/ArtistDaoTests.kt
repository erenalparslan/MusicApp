package com.erenalparslan.musicapp.data.local


import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.erenalparslan.musicapp.DataGenerator
import com.erenalparslan.musicapp.data.local.dao.ArtistDao
import com.erenalparslan.musicapp.data.local.entities.Artist
import com.erenalparslan.musicapp.data.local.entities.Track
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArtistDaoTests {

    lateinit var  artists :List<Artist>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MusicAppDatabase
    private lateinit var dao: ArtistDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MusicAppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.artisDao
    }

    @Test
    fun isDatabaseNotNull(){
        assertThat(database).isNotNull()
    }

    @Test
    fun isDaoNotNull(){
        assertThat(dao).isNotNull()
    }

    @Test
    fun insertArtistSuccess() = runTest {
        val artist = DataGenerator.getArtist("ABC")
        dao.insert(artist)
        assertThat(dao.getArtist(artist.name)).isNotNull()
    }

    @Test
    fun insertLongNameSuccess() = runTest {
        val artist = DataGenerator.getArtist("ABCDEFGHIJKLMNOPRSTUCYZXW")
        dao.insert(artist)
        assertThat(dao.getArtist(artist.name).name).isEqualTo("ABCDEFGHIJKLMNOPRSTUCYZXW")
    }

    @Test
    fun insertEmptyNameArtistSuccess() = runTest {
        val artist = DataGenerator.getArtist("")
        dao.insert(artist)
        assertThat(dao.getArtist(artist.name)).isNotNull()
    }

    @Test
    fun deleteEmptyNameArtistSuccess() = runTest {
        val artist = DataGenerator.getArtist("")
        dao.insert(artist)
        dao.delete(artist.name)
        assertThat(dao.getArtist(artist.name)).isNull()
    }

    @Test
    fun insertAllArtistSuccess() = runTest {
        val artist = DataGenerator.getArtist("ABC")
        val artist2 = DataGenerator.getArtist("ABC2")
        artists= listOf(artist,artist2)
        dao.insertAll(artists)
        assertThat(dao.getArtist(artist.name)).isNotNull()
        assertThat(dao.getArtist(artist2.name)).isNotNull()
    }

    @Test
    fun deleteArtistArtistNameNull() = runTest {
        val artist = DataGenerator.getArtist("ABC")
        val artist2 = DataGenerator.getArtist("ABC")
        // same ids
        artists= listOf(artist,artist2)
        dao.insertAll(artists)
        dao.delete(artist.name)
        assertThat(dao.getArtist(artist.name)).isNull()
        assertThat(dao.getArtist(artist2.name)).isNull()
    }

    @Test
    fun deleteArtistArtistNameNotNull() = runTest {
        val artist = DataGenerator.getArtist("ABC")
        val artist2 = DataGenerator.getArtist("ABC1")
        // same ids
        artists= listOf(artist,artist2)
        dao.insertAll(artists)
        dao.delete(artist.name)
        assertThat(dao.getArtist(artist.name)).isNull()
        assertThat(dao.getArtist(artist2.name)).isNotNull()
    }

    @Test
    fun isGetArtistReturnArtist() = runTest {
        val artist = DataGenerator.getArtist("ABC")
        dao.insert(artist)
        assertThat(dao.getArtist(artist.name)).isInstanceOf(Artist::class.java)
        assertThat(dao.getArtist(artist.name)).isNotInstanceOf(Track::class.java)
    }


    @After
    fun teardown() {
        database.close()
    }
}
