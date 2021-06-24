package id.fadillah.jetpacksubmission.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.core.vo.Resource

class HomeViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getUpcomingMovie(): LiveData<Resource<List<MovieEntity>>> =
        useCase.getUpcoming().asLiveData()

    fun getNowPlaying(): LiveData<Resource<List<MovieEntity>>> =
        useCase.getNowPlaying().asLiveData()

    fun getPopular(): LiveData<Resource<List<MovieEntity>>> = useCase.getPopular().asLiveData()
    fun getTopRated(): LiveData<Resource<List<MovieEntity>>> = useCase.getTopRated().asLiveData()
}