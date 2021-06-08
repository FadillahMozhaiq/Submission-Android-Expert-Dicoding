package id.fadillah.jetpacksubmission.ui.activity.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
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
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    private lateinit var viewModel: DetailViewModel
    private val idMovie: Int = 460465
    private val idTv: Int = 1399

    @Before
    fun setUp() {
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie
        `when`(useCase.getDetailMovie(idMovie)).thenReturn(movie)
        viewModel.getDetailMovie(idMovie).observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)

        assertNotNull(dummyDetailMovie)
        assertEquals(dummyDetailMovie.data?.id, movie.value?.data?.id)
        assertEquals(dummyDetailMovie.data?.title, movie.value?.data?.title)
        assertEquals(dummyDetailMovie.data?.posterPath, movie.value?.data?.posterPath)
        assertEquals(dummyDetailMovie.data?.overview, movie.value?.data?.overview)
    }

    @Test
    fun getDetailTv() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTv())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailTvShow
        `when`(useCase.getDetailTv(idTv)).thenReturn(movie)
        viewModel.getDetailTv(idTv).observeForever(observer)
        verify(observer).onChanged(dummyDetailTvShow)

        assertNotNull(dummyDetailTvShow)
        assertEquals(dummyDetailTvShow.data?.id, movie.value?.data?.id)
        assertEquals(dummyDetailTvShow.data?.title, movie.value?.data?.title)
        assertEquals(dummyDetailTvShow.data?.posterPath, movie.value?.data?.posterPath)
        assertEquals(dummyDetailTvShow.data?.overview, movie.value?.data?.overview)
    }
}