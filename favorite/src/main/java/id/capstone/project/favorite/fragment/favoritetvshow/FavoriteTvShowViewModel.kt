package id.capstone.project.favorite.fragment.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase

class FavoriteTvShowViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getAllFavoriteTv(): LiveData<List<MovieEntity>> = useCase.getAllFavoriteTv().asLiveData()
}