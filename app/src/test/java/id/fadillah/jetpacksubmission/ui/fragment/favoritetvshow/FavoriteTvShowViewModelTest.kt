package id.fadillah.jetpacksubmission.ui.fragment.favoritetvshow

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: FavoriteTvShowViewModel

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(useCase)
    }

    @Test
    fun getAllFavoriteTv() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(useCase.getAllFavoriteTv()).thenReturn(movies)
        val movieEntity = viewModel.getAllFavoriteTv().value
        verify(useCase).getAllFavoriteTv()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getAllFavoriteTv().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}