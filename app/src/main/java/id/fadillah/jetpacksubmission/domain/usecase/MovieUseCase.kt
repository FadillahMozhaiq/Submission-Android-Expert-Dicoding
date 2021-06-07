package id.fadillah.jetpacksubmission.domain.usecase

import androidx.lifecycle.LiveData
import id.fadillah.jetpacksubmission.data.model.MovieEntity
import id.fadillah.jetpacksubmission.vo.Resource

interface MovieUseCase {
    //     Home Fragment
    fun getUpcoming(): LiveData<Resource<List<MovieEntity>>>
    fun getNowPlaying(): LiveData<Resource<List<MovieEntity>>>
    fun getPopular(): LiveData<Resource<List<MovieEntity>>>
    fun getTopRated(): LiveData<Resource<List<MovieEntity>>>

    //    Explore Fragment
    fun getMovieExplore(query: String): LiveData<Resource<List<MovieEntity>>>
    fun getTvExplore(query: String): LiveData<Resource<List<MovieEntity>>>
    fun getPersonExplore(query: String): LiveData<Resource<List<MovieEntity>>>
    fun getCompanyExplore(query: String): LiveData<Resource<List<MovieEntity>>>
    fun getMultiSearch(query: String): LiveData<Resource<List<MovieEntity>>>

    //    MovieFragment
    fun getTrendingMovie(): LiveData<Resource<List<MovieEntity>>>

    //    Tv Fragment
    fun getTrendingTv(): LiveData<Resource<List<MovieEntity>>>

    //    Detail Activity
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getDetailTv(id: Int): LiveData<Resource<MovieEntity>>
}