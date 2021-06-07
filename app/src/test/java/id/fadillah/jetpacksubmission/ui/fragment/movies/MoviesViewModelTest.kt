package id.fadillah.jetpacksubmission.ui.fragment.movies

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase
    private lateinit var viewModel: MoviesViewModel
    private val dummyMovies = DataDummy.getMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(useCase)
    }

    @Test
    fun getTrendingMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getTrendingMovie()).thenReturn(movies)
        val movieEntity = viewModel.getTrendingMovies().value
        verify(useCase).getTrendingMovie()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getTrendingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}