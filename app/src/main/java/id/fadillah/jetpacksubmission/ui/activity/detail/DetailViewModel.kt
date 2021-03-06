package id.fadillah.jetpacksubmission.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.core.vo.Resource
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> =
        useCase.getDetailMovie(id).asLiveData()

    fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> = useCase.getDetailTv(id).asLiveData()
    fun checkFavorite(id: Int, type: Int): LiveData<Boolean> =
        useCase.isItFavorite(id, type).asLiveData()

    fun setFavorite(favorite: Boolean, id: Int, type: Int) =
        viewModelScope.launch {
            useCase.setFavorite(favorite, id, type)
        }
}