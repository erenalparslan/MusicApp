package com.erenalparslan.musicapp.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.erenalparslan.musicapp.DataGenerator
import com.erenalparslan.musicapp.data.local.dao.AlbumDao
import com.erenalparslan.musicapp.data.local.entities.Album
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AlbumDaoTests {

    lateinit var  albums :List<Album>

    @get:Rule
        var instantTaskExecute=InstantTaskExecutorRule()

    private lateinit var database :MusicAppDatabase
    lateinit var dao :AlbumDao


    @Before
    fun setup(){
        database= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),MusicAppDatabase::class.java).allowMainThreadQueries().build()
        dao=database.albumDao

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
    fun insertAlbumSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        assertThat(dao.getAll()).hasSize(1)
    }

    @Test
    fun insertLongAlbumNameSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABCDEFGHIJKLMNOPRSTUCYZXWABCDEFGHIJKLMNOPRSTUCYZXW")
        dao.insert(album)
        assertThat(dao.getAll()).hasSize(1)
    }

    @Test
    fun insertAlbumFailure() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        val album2 = DataGenerator.getAlbum("ABC2")
        dao.insert(album)
        dao.insert(album2)
        assertThat(dao.getAll().size).isEqualTo(2)
        assertThat(dao.getAll()).isNotEqualTo(1)
        assertThat(dao.getAll().size).isNotEqualTo(3)
    }

    @Test
    fun insertAllAlbumSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        val album1 = DataGenerator.getAlbum("ABC1")
        albums= listOf(album,album1)
        dao.insertAll(albums)
        assertThat(dao.getAll()).hasSize(2)
    }

    @Test
    fun insertAllAlbumFailure() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        val album1 = DataGenerator.getAlbum("ABC1")
        albums= listOf(album,album1)
        dao.insertAll(albums)
        assertThat(dao.getAll().size).isEqualTo(2)
        assertThat(dao.getAll().size).isNotEqualTo(1)
        assertThat(dao.getAll().size).isNotEqualTo(0)
    }

    @Test
    fun duplicateAlbumScenarioSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        dao.insert(album)
        assertThat(dao.getAll().size).isEqualTo(1)
    }

    @Test
    fun duplicateAlbumScenarioFailure() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        dao.insert(album)
        dao.insert(album)
        assertThat(dao.getAll().size).isNotEqualTo(3)
        assertThat(dao.getAll().size).isNotEqualTo(2)
        assertThat(dao.getAll().size).isEqualTo(1)
    }

    @Test
    fun deleteAlbumScenarioSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        dao.delete(album.name)
        assertThat(dao.getAll().size).isEqualTo(0)
    }

    @Test
    fun deleteAlbumScenarioFailure() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        dao.delete(album.artistName) // Passing Wrong Name
        assertThat(dao.getAll().size).isEqualTo(1)
        assertThat(dao.getAll().size).isNotEqualTo(0)
    }

    @Test
    fun insertAlbumAllScenarioTest() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        val album2 = DataGenerator.getAlbum("ABC2")
        dao.insertAll(listOf(album, album2))
        assertThat(dao.getAll().size).isEqualTo(2)
    }

    @Test
    fun isExistAlbumScenarioSuccess() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        val exist = dao.isExist(album.name)
        assertThat(exist).isTrue()
    }

    @Test
    fun isExistAlbumScenarioFailure() = runTest {
        val album = DataGenerator.getAlbum("ABC")
        dao.insert(album)
        dao.delete(album.name)
        val exist = dao.isExist(album.name)
        assertThat(exist).isFalse()
    }

    @After
    fun teardown() {
        database.close()
    }

}