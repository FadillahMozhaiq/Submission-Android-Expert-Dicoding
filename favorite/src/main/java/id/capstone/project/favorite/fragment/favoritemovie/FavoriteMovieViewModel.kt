package id.capstone.project.favorite.fragment.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getAllFavoriteMovie(): LiveData<List<MovieEntity>> =
        useCase.getAllFavoriteMovie().asLiveData()
}