package id.fadillah.jetpacksubmission.ui.fragment.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase

class FavoriteTvShowViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getAllFavoriteTv(): LiveData<PagedList<MovieEntity>> = useCase.getAllFavoriteTv()
}