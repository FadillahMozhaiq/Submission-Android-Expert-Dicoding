package id.fadillah.jetpacksubmission.ui.fragment.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.MovieRepository
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun getUpcomingMovie() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(useCase.getUpcoming()).thenReturn(movies)
        val movieEntity = viewModel.getUpcomingMovie().value?.data
        verify(useCase).getUpcoming()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getUpcomingMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getNowPlaying() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(useCase.getNowPlaying()).thenReturn(movies)
        val movieEntity = viewModel.getNowPlaying().value?.data
        verify(useCase).getNowPlaying()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getPopular() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(useCase.getPopular()).thenReturn(movies)
        val movieEntity = viewModel.getPopular().value?.data
        verify(useCase).getPopular()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getPopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTopRated() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(useCase.getTopRated()).thenReturn(movies)
        val movieEntity = viewModel.getTopRated().value?.data
        verify(useCase).getTopRated()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getTopRated().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}