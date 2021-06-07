package id.fadillah.jetpacksubmission.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> = useCase.getDetailMovie(id)
    fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> = useCase.getDetailTv(id)
    fun checkFavorite(id: Int): LiveData<Boolean> = useCase.isItFavorite(id)
    fun setFavorite(favorite: Boolean, id: Int) =
        viewModelScope.launch {
            useCase.setFavorite(favorite, id)
        }
}