package com.erenalparslan.musicapp.data.remote


import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.erenalparslan.musicapp.BuildConfig
import com.erenalparslan.musicapp.di.NetworkModule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class RetrofitClientTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var instance: Retrofit? = RetrofitClient().retrofit
    private var lastFMApi: MusicAppApi? = null

    @Before
    fun setup() {

        lastFMApi = instance?.create(MusicAppApi::class.java)
    }

    @Test
    fun isBaseUrlSame() {

        assert(instance?.baseUrl().toString() == NetworkModule.BASE_URL)
    }

    @Test
    fun searchArtist_notNull() = runTest {
        // Execute the API call
        val response = lastFMApi?.searchArtist("Edis", BuildConfig.API_KEY)
        assert(response != null)
    }

    @Test
    fun emptyArtist() = runTest {
        // Execute the API call
        val response = lastFMApi?.searchArtist("", BuildConfig.API_KEY)
        assertThat(response?.results).isEqualTo(null)
    }

    @Test
    fun searchArtistPaged() = runTest {
        // Execute the API call
        val response = lastFMApi?.searchArtistPaged("Edis", BuildConfig.API_KEY,5,10)
        assert(response != null)
    }


    @Test
    fun searchArtist_Success() = runTest {

        val response = lastFMApi?.searchArtist("Edis", BuildConfig.API_KEY)
        assertThat(response?.results?.artistmatches?.artist?.size).isGreaterThan(0)
    }

    @Test
    fun searchEmptyArtist() = runTest {

        val response = lastFMApi?.searchArtist("", BuildConfig.API_KEY)
        assertThat(response?.results?.artistmatches?.artist?.size).isEqualTo(null)
    }

    @Test
    fun getTopAlbum_Success() = runTest {

        val response = lastFMApi?.getTopAlbums("Simge", BuildConfig.API_KEY)
        assert(response != null)
        assertThat(response?.topalbums?.album?.size).isGreaterThan(0)
    }

    @Test
    fun getTopAlbumEmptyName_Success() = runTest {

        val response = lastFMApi?.getTopAlbums("", BuildConfig.API_KEY)
        assert(response != null)
        assertThat(response?.topalbums?.album?.size).isEqualTo(null)
    }

    @Test
    fun getAlbumInfo_Success() = runTest {

        val response = lastFMApi?.getAlbumInfo("Sezen Aksu", "Git", BuildConfig.API_KEY)
        assert(response != null)
        assertThat(response?.album?.tracks?.tracks?.size).isGreaterThan(0)
    }

    @Test
    fun getAlbumInfoEmptyArtist_Null() = runTest {

        val response = lastFMApi?.getAlbumInfo("", "", BuildConfig.API_KEY)
        assertThat(response).isEqualTo(null)
    }


    @After
    fun tearDown() {
        instance = null
        lastFMApi = null
    }
}
