package id.fadillah.jetpacksubmission.ui.fragment.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.core.vo.Resource

class MoviesViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getTrendingMovies(): LiveData<Resource<List<MovieEntity>>> =
        useCase.getTrendingMovie().asLiveData()
}