package id.fadillah.jetpacksubmission.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.vo.Resource

class DetailViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> = useCase.getDetailMovie(id)
    fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>> = useCase.getDetailTv(id)
}