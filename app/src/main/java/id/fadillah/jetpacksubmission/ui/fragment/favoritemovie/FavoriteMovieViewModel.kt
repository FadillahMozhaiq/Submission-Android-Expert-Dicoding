package id.fadillah.jetpacksubmission.ui.fragment.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getAllFavoriteMovie(): LiveData<List<MovieEntity>> =
        useCase.getAllFavoriteMovie().asLiveData()
}