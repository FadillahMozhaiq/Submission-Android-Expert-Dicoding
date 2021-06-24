package id.fadillah.jetpacksubmission.ui.fragment.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fadillah.jetpacksubmission.core.data.model.MovieEntity
import id.fadillah.jetpacksubmission.core.domain.usecase.MovieUseCase
import id.fadillah.jetpacksubmission.core.vo.Resource

class TvViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun getTrendingTv(): LiveData<Resource<List<MovieEntity>>> =
        useCase.getTrendingTv().asLiveData()
}