package id.fadillah.jetpacksubmission.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource

class HomeViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getUpcoming()
    fun getNowPlaying(): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getNowPlaying()
    fun getPopular(): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getPopular()
    fun getTopRated(): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getTopRated()
}