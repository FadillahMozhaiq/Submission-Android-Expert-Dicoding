package id.fadillah.jetpacksubmission.ui.fragment.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
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

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase
    private lateinit var viewModel: HomeViewModel
    private val dummyMovies = DataDummy.getMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun getUpcomingMovie() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getUpcoming()).thenReturn(movies)
        val movieEntity = viewModel.getUpcomingMovie().value
        verify(useCase).getUpcoming()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getUpcomingMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getNowPlaying() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getNowPlaying()).thenReturn(movies)
        val movieEntity = viewModel.getNowPlaying().value
        verify(useCase).getNowPlaying()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getPopular() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getPopular()).thenReturn(movies)
        val movieEntity = viewModel.getPopular().value
        verify(useCase).getPopular()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getPopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTopRated() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(useCase.getTopRated()).thenReturn(movies)
        val movieEntity = viewModel.getTopRated().value
        verify(useCase).getTopRated()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getTopRated().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}