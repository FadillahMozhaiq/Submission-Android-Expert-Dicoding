package id.fadillah.jetpacksubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    private val dummyMovies = DataDummy.getMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var useCase: MovieUseCase
    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>
    @Mock
    private lateinit var observerDetail: Observer<MovieEntity>
    private val query = "Marvel"
    private val idMovie: Int = 460465
    private val idTv: Int = 1399
    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyTv = DataDummy.getDetailTv()

    @Test
    fun getUpcoming() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getUpcoming()).thenReturn(movies)
        val movieEntity = useCase.getUpcoming().value
        verify(useCase).getUpcoming()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getUpcoming().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getNowPlaying() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getNowPlaying()).thenReturn(movies)
        val movieEntity = useCase.getNowPlaying().value
        verify(useCase).getNowPlaying()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getPopular() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getPopular()).thenReturn(movies)
        val movieEntity = useCase.getPopular().value
        verify(useCase).getPopular()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getPopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTopRated() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getTopRated()).thenReturn(movies)
        val movieEntity = useCase.getTopRated().value
        verify(useCase).getTopRated()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getTopRated().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMovieExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getMovieExplore(query)).thenReturn(movies)
        val movieEntity = useCase.getMovieExplore(query).value
        verify(useCase).getMovieExplore(query)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getMovieExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getTvExplore(query)).thenReturn(movies)
        val movieEntity = useCase.getTvExplore(query).value
        verify(useCase).getTvExplore(query)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getTvExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getPersonExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getPersonExplore(query)).thenReturn(movies)
        val movieEntity = useCase.getPersonExplore(query).value
        verify(useCase).getPersonExplore(query)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getPersonExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getCompanyExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getCompanyExplore(query)).thenReturn(movies)
        val movieEntity = useCase.getCompanyExplore(query).value
        verify(useCase).getCompanyExplore(query)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getCompanyExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMultiSearch() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getMultiSearch(query)).thenReturn(movies)
        val movieEntity = useCase.getMultiSearch(query).value
        verify(useCase).getMultiSearch(query)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getMultiSearch(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTrendingMovie() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getTrendingMovie()).thenReturn(movies)
        val movieEntity = useCase.getTrendingMovie().value
        verify(useCase).getTrendingMovie()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getTrendingMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTrendingTv() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getTrendingTv()).thenReturn(movies)
        val movieEntity = useCase.getTrendingTv().value
        verify(useCase).getTrendingTv()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        useCase.getTrendingTv().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(useCase.getDetailMovie(idMovie)).thenReturn(movie)
        val movieEntity = useCase.getDetailMovie(idMovie).value as MovieEntity
        verify(useCase).getDetailMovie(idMovie)

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        useCase.getDetailMovie(idMovie).observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv() {
        val tv = MutableLiveData<MovieEntity>()
        tv.value = dummyTv

        `when`(useCase.getDetailTv(idTv)).thenReturn(tv)
        val tvEntity = useCase.getDetailTv(idTv).value as MovieEntity
        verify(useCase).getDetailTv(idTv)

        assertNotNull(tvEntity)
        assertEquals(dummyTv.id, tvEntity.id)
        assertEquals(dummyTv.title, tvEntity.title)
        assertEquals(dummyTv.posterPath, tvEntity.posterPath)
        assertEquals(dummyTv.overview, tvEntity.overview)

        useCase.getDetailTv(idTv).observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyTv)
    }
}