package id.fadillah.jetpacksubmission.ui.fragment.favoritemovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
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
class FavoriteMovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: FavoriteMovieViewModel

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(useCase)
    }

    @Test
    fun getAllFavoriteMovie() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(useCase.getAllFavoriteMovie()).thenReturn(movies)
        val movieEntity = viewModel.getAllFavoriteMovie().value
        verify(useCase).getAllFavoriteMovie()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getAllFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}