package id.fadillah.jetpacksubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.source.local.LocalDataSource
import id.fadillah.jetpacksubmission.data.source.local.model.MovieDatabaseEntity
import id.fadillah.jetpacksubmission.data.source.network.RemoteDataSource
import id.fadillah.jetpacksubmission.utils.LiveDataTestUtils
import id.fadillah.jetpacksubmission.utils.PagedListUtil
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
import id.fadillah.jetpacksubmission.vo.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)

    private val repository = FakeMovieRepository(local, remote)

    private val query = "Marvel"
    private val idMovie: Int = 460465
    private val idTv: Int = 1399

    private val dummyMovies = DataDummy.getMovie()
    private val dummyTv = DataDummy.getDetailTvDatabase()

    @Test
    fun getUpcoming() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllUpcomingMovies()).thenReturn(dataSourceFactory)
        repository.getUpcoming()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllUpcomingMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getNowPlaying() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllNowPlayingMovies()).thenReturn(dataSourceFactory)
        repository.getNowPlaying()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllNowPlayingMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getPopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllPopularMovies()).thenReturn(dataSourceFactory)
        repository.getPopular()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllPopularMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTopRated() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllTopRatedMovies()).thenReturn(dataSourceFactory)
        repository.getTopRated()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllTopRatedMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieExplore() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getMovieExplore(query)).thenReturn(dataSourceFactory)
        repository.getMovieExplore(query)

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getMovieExplore(query)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvExplore() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getTvExplore(query)).thenReturn(dataSourceFactory)
        repository.getTvExplore(query)

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getTvExplore(query)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getPersonExplore() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getPersonExplore(query)).thenReturn(dataSourceFactory)
        repository.getPersonExplore(query)

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getPersonExplore(query)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getCompanyExplore() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getCompanyExplore(query)).thenReturn(dataSourceFactory)
        repository.getCompanyExplore(query)

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getCompanyExplore(query)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMultiSearch() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getMultiSearch(query)).thenReturn(dataSourceFactory)
        repository.getMultiSearch(query)

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getMultiSearch(query)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTrendingMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllTrendingMovies()).thenReturn(dataSourceFactory)
        repository.getTrendingMovie()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllTrendingMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTrendingTv() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllTrendingTv()).thenReturn(dataSourceFactory)
        repository.getTrendingTv()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllTrendingTv()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyEntity = MutableLiveData<MovieDatabaseEntity>()
        dummyEntity.value = DataDummy.getDetailMovieDatabase()
        `when`(local.getMovieById(idMovie)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtils.getValue(repository.getDetailMovie(idMovie))
        verify(local).getMovieById(idMovie)
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.title)
        assertEquals(dummyMovies[0].title, movieEntity.data?.title)
    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<MovieDatabaseEntity>()
        dummyEntity.value = DataDummy.getDetailTvDatabase()
        `when`(local.getMovieById(idTv)).thenReturn(dummyEntity)

        val tvEntity = LiveDataTestUtils.getValue(repository.getDetailTv(idTv))
        verify(local).getMovieById(idTv)
        assertNotNull(tvEntity.data)
        assertNotNull(tvEntity.data?.title)
        assertEquals(dummyTv.title, tvEntity.data?.title)
    }

    @Test
    fun getAllFavoriteMovie() {
        runBlocking {
            local.setFavorite(true, idMovie, 0)
        }
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getAllFavoriteMovie()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteTv() {
        runBlocking {
            local.setFavorite(true, idTv, 0)
        }
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDatabaseEntity>
        `when`(local.getAllFavoriteTv()).thenReturn(dataSourceFactory)
        repository.getAllFavoriteTv()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllFavoriteTv()
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
    }
}