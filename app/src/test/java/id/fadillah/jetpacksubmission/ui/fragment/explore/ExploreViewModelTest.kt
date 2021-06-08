package id.fadillah.jetpacksubmission.ui.fragment.explore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.fadillah.jetpacksubmission.data.MovieRepository
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.utils.dummy.DataDummy
import id.fadillah.jetpacksubmission.vo.Resource
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExploreViewModelTest {

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var viewModel: ExploreViewModel
    private val dummyMovies = DataDummy.getMovie()
    private val query = "Marvel"
    private val queryPerson = "Leo"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ExploreViewModel(useCase)
    }

    @Test
    fun getMovieExplore() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(useCase.getMovieExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getMovieExplore(query).value?.data
        verify(useCase).getMovieExplore(query)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getMovieExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvExplore() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyTv

        `when`(useCase.getTvExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getTvExplore(query).value?.data
        verify(useCase).getTvExplore(query)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getTvExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun getPersonExplore() {
        val dummyPerson = Resource.success(pagedList)
        `when`(dummyPerson.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyPerson

        `when`(useCase.getPersonExplore(queryPerson)).thenReturn(movies)
        val movieEntity = viewModel.getPersonExplore(queryPerson).value?.data
        verify(useCase).getPersonExplore(queryPerson)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getPersonExplore(queryPerson).observeForever(observer)
        verify(observer).onChanged(dummyPerson)
    }

    @Test
    fun getCompanyExplore() {
        val dummyCompany = Resource.success(pagedList)
        `when`(dummyCompany.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyCompany

        `when`(useCase.getCompanyExplore(query)).thenReturn(movies)
        val movieEntity = viewModel.getCompanyExplore(query).value?.data
        verify(useCase).getCompanyExplore(query)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getCompanyExplore(query).observeForever(observer)
        verify(observer).onChanged(dummyCompany)
    }

    @Test
    fun getMultiSearch() {
        val dummyAny = Resource.success(pagedList)
        `when`(dummyAny.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyAny

        `when`(useCase.getMultiSearch(query)).thenReturn(movies)
        val movieEntity = viewModel.getMultiSearch(query).value?.data
        verify(useCase).getMultiSearch(query)
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getMultiSearch(query).observeForever(observer)
        verify(observer).onChanged(dummyAny)
    }
}