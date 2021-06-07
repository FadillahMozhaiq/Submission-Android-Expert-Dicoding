package id.fadillah.jetpacksubmission.ui.fragment.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource

class ExploreViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getMovieExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getMovieExplore(query)
    fun getTvExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getTvExplore(query)
    fun getPersonExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getPersonExplore(query)
    fun getCompanyExplore(query: String): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getCompanyExplore(query)
    fun getMultiSearch(query: String): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getMultiSearch(query)
}