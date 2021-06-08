package id.fadillah.jetpacksubmission.ui.fragment.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
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
class MoviesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(useCase)
    }

    @Test
    fun getTrendingMovies() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(useCase.getTrendingMovie()).thenReturn(movies)
        val movieEntity = viewModel.getTrendingMovies().value?.data
        verify(useCase).getTrendingMovie()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getTrendingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}