package id.fadillah.jetpacksubmission.ui.fragment.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.core.vo.Resource

class ExploreViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getMovieExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        useCase.getMovieExplore(query).asLiveData()

    fun getTvExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        useCase.getTvExplore(query).asLiveData()

    fun getPersonExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        useCase.getPersonExplore(query).asLiveData()

    fun getCompanyExplore(query: String): LiveData<Resource<List<MovieEntity>>> =
        useCase.getCompanyExplore(query).asLiveData()

    fun getMultiSearch(query: String): LiveData<Resource<List<MovieEntity>>> =
        useCase.getMultiSearch(query).asLiveData()
}