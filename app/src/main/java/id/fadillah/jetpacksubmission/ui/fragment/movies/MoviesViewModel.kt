package id.fadillah.jetpacksubmission.ui.fragment.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource

class MoviesViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getTrendingMovies(): LiveData<Resource<PagedList<MovieEntity>>> = useCase.getTrendingMovie()
}