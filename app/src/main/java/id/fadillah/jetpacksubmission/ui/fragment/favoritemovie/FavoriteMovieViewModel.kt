package id.fadillah.jetpacksubmission.ui.fragment.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource

class FavoriteMovieViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getAllFavoriteMovie(): LiveData<PagedList<MovieEntity>> = useCase.getAllFavoriteMovie()
}