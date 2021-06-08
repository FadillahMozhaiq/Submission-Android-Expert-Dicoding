package id.fadillah.jetpacksubmission.ui.fragment.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource
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
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel(useCase)
    }

    @Test
    fun getTrendingTv() {
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyTvShow

        Mockito.`when`(useCase.getTrendingTv()).thenReturn(movies)
        val movieEntity = viewModel.getTrendingTv().value?.data
        verify(useCase).getTrendingTv()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(5, movieEntity?.size)

        viewModel.getTrendingTv().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}