package id.fadillah.jetpacksubmission.ui.fragment.explore

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
class ExploreViewModelTest {

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var useCase: MovieUseCase
    private lateinit var viewModel: ExploreViewModel
    private val dummyMovies = DataDummy.getMovie()
    private val query = "Marvel"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ExploreViewModel(useCase)
    }

    @Test
    fun getMovieExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getMovieExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getMovieExplore(query).value
        verify(useCase).getMovieExplore(query)
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getMovieExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getTvExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getTvExplore(query).value
        verify(useCase).getTvExplore(query)
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getTvExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getPersonExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getPersonExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getPersonExplore(query).value
        verify(useCase).getPersonExplore(query)
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getPersonExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getCompanyExplore() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getCompanyExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getCompanyExplore(query).value
        verify(useCase).getCompanyExplore(query)
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getCompanyExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMultiSearch() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(useCase.getMultiSearch(query)).thenReturn(movies)
        val movieEntity = viewModel.getMultiSearch(query).value
        verify(useCase).getMultiSearch(query)
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(20, movieEntity?.size)

        viewModel.getMultiSearch(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}