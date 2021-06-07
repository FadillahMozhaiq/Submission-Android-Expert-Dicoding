package id.fadillah.jetpacksubmission.ui.activity.detail

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
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    private lateinit var viewModel: DetailViewModel
    private val idMovie: Int = 460465
    private val idTv: Int = 1399
    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyTv = DataDummy.getDetailTv()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieUseCase)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieUseCase.getDetailMovie(idMovie)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie(idMovie).value as MovieEntity
        verify(movieUseCase).getDetailMovie(idMovie)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        viewModel.getDetailMovie(idMovie).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv() {
        val tv = MutableLiveData<MovieEntity>()
        tv.value = dummyTv

        `when`(movieUseCase.getDetailTv(idTv)).thenReturn(tv)
        val tvEntity = viewModel.getDetailTv(idTv).value as MovieEntity
        verify(movieUseCase).getDetailTv(idTv)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.id, tvEntity.id)
        assertEquals(dummyTv.title, tvEntity.title)
        assertEquals(dummyTv.posterPath, tvEntity.posterPath)
        assertEquals(dummyTv.overview, tvEntity.overview)

        viewModel.getDetailTv(idTv).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}