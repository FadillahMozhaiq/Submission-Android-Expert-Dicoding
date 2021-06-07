package id.fadillah.jetpacksubmission.ui.fragment.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase
    private lateinit var viewModel: TvViewModel
    private val dummyMovies = DataDummy.getMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvViewModel(useCase)
    }

    @Test
    fun getTrendingTv() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getTrendingTv()).thenReturn(movies)
        val movieEntity = viewModel.getTrendingTv().value
        verify(useCase).getTrendingTv()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getTrendingTv().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}